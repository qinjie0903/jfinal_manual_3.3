package demo;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.i18n.I18nInterceptor;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.cache.EhCache;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.plugin.activerecord.tx.TxByActionKeyRegex;
import com.jfinal.plugin.activerecord.tx.TxByActionKeys;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// 此方法用来配置JFinal的常量
		me.setDevMode(true);// 开发模式

		me.setUrlParaSeparator("_");// 设置参数分隔符 默认为“-”
		
		//PropKit
		//第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
		/*PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode"));*/
		
		me.setBaseUploadPath("/upload");	//文件上传基础路径
		me.setBaseDownloadPath("/download");	//文件下载基础路径
		me.setViewType(ViewType.JFINAL_TEMPLATE);//设置渲染视图（默认：JFINAL_TEMPLATE）
		
		// 配置资源文件默认i18n
		me.setI18nDefaultBaseName("i18n");
		
		
	}

	@Override
	public void configRoute(Routes me) {
		// 此方法用来配置JFinal访问路由
		me.setBaseViewPath("/view");//

		me.addInterceptor(new FrontInterceptor());
		me.add("/hello", HelloControl.class);

		me.add(new FrontRoutes());// 前端路由
		me.add(new AdminRoutes());// 后端路由

	}

	@Override
	public void configEngine(Engine me) {
		// 此方法用来配置Template Engine

		me.addSharedFunction("/_view/common/__layout.html");
		me.addSharedFunction("/_view/common/_paginate.html");
		me.addSharedFunction("/_view/_admin/common/__admin_layout.html");

	}

	@Override
	public void configPlugin(Plugins me) {
		// 此方法用来配置 Plugin
		loadPropertyFile("config.txt");
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		me.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setBaseSqlTemplatePath(PathKit.getRootClassPath());//设置sql文件存放的基础路径
		arp.addSqlTemplate("demo.sql");//添加外部sql模板
		
		// 实现热加载 (如下2种方法)，如果不配置则默认configConstant中的me.setDevMode(true);
		arp.setDevMode(true);
		arp.getEngine().setDevMode(true);
		
		//_MappingKit.mapping(arp);
		me.add(arp);
		// 配置Postgresql方言
		/*arp.setDialect(new PostgreSqlDialect());
		arp.setDialect(new MysqlDialect());
		arp.setDialect(new SqlServerDialect());
		arp.setDialect(new Sqlite3Dialect());
		arp.setDialect(new AnsiSqlDialect());
		arp.setDialect(new OracleDialect());*/
		arp.addMapping("user", User.class);
		
		// 多数据源的配置仅仅是如下第二个参数指定一次复合主键名称
		arp.addMapping("user_role", "userId,roleId", UserRole.class);
		
		// 同时指定复合主键值即可查找记录
		UserRole.dao.findById(123,456);
		
		// 同时指定复合主键值即可删除记录
		UserRole.dao.deleteById(123,456);
		
		
		//PropKit
		//非第一次使用use加载的配置，需要通过每次使用use来指定配置文件名再来取值
		String redisHost = PropKit.use("redis_config.txt").get("host");
		int redisPort = PropKit.use("redis_config.txt").getInt("port");
		RedisPlugin rp = new RedisPlugin("myRedis", redisHost,redisPort);
		me.add(rp);
		
		//非第一次使用 use加载的配置，也可以先得到一个Prop对象，再通过该对象来获取值
		Prop p = PropKit.use("db_config.txt");
		DruidPlugin dp2 = new DruidPlugin(p.get("jdbcUrl"), p.get("user"),p.get("password"));
		me.add(dp2);
		PropKit.useless("db_config.txt");//清除缓存配置内容
		
		//druid数据源插件
		DruidPlugin dp3 = new DruidPlugin("jdbc:mysql://localhost/db_name", "username", "password");
		me.add(dp3);
		
		//ActiveRecord插件
		ActiveRecordPlugin arp3 = new ActiveRecordPlugin(dp3);
		me.add(arp3);
		arp3.addMapping("user", User.class);	//默认主键：id
		arp3.addMapping("article", "article_id", Article.class); //自定义主键：article_id
				
		me.add(new EhCachePlugin());
		
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// 此方法用来配置全局拦截器,拦截所有action请求，除非使用@clear在Controller中清除
		me.add(new AuthInterceptor());	//全局Global配置粒度（Global，Inject，Class，Method）
		
		
		// 添加控制层全局拦截器
		me.addGlobalActionInterceptor(new GlobalActionInterceptor());
		
		// 添加业务层全局拦截器
		me.addGlobalServiceInterceptor(new GlobalServiceInterceptor());

		// 为兼容老版本保留的方法，功能与addGlobalActionInterceptor完全一样
		me.add(new GlobalActionInterceptor());
		
		// 对actionKeys，actionKey正则，actionMethods，actionMethod正则声明事务
		me.add(new TxByMethodRegex("(.*save.*|.*update.*)"));
		me.add(new TxByMethods("save","update"));
		me.add(new TxByActionKeyRegex("/trans.*"));
		me.add(new TxByActionKeys("/tx/save","/tx/update"));
		
		
		// 国际化
		// 先将I18nInterceptor配置成全局拦截器
		me.add(new I18nInterceptor());
	
	}

	@Override
	public void configHandler(Handlers me) {
		// 此方法用来配置 Handler
		me.add(new ResourceHandler());
	}

	@Override
	public void afterJFinalStart() {
		//启动完成后调用
		super.afterJFinalStart();
	}

	@Override
	public void beforeJFinalStop() {
		//系统关闭前调用
		super.beforeJFinalStop();
	}

}
