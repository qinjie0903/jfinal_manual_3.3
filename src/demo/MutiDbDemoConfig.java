package demo;

import java.util.List;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
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
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class MutiDbDemoConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// 此方法用来配置JFinal的常量
		me.setDevMode(true);// 开发模式
	}

	@Override
	public void configRoute(Routes me) {

	}

	@Override
	public void configEngine(Engine me) {

	}

	@Override
	public void configPlugin(Plugins me) {
		// mysql数据源
		DruidPlugin dsMysql = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		me.add(dsMysql);

		// mysql ActiveRecordPlugin 实例，并指定configName为mysql
		ActiveRecordPlugin arpMysql = new ActiveRecordPlugin("mysql", dsMysql);
		me.add(arpMysql);
		arpMysql.setCache(new EhCache());
		arpMysql.addMapping("user", User.class);

		// oracle数据源
		DruidPlugin dsOracle = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		me.add(dsOracle);

		// oracle ActiveRecordPlugin 实例，并指定configName为oracle
		ActiveRecordPlugin arpOracle = new ActiveRecordPlugin("oracle", dsOracle);
		me.add(arpOracle);
		arpMysql.setDialect(new OracleDialect());
		arpOracle.setTransactionLevel(8);
		arpMysql.addMapping("blog", Blog.class);

	}

	@Override
	public void configInterceptor(Interceptors me) {

	}

	@Override
	public void configHandler(Handlers me) {
	}

	@Override
	public void afterJFinalStart() {
		// 启动完成后调用
		super.afterJFinalStart();
	}

	@Override
	public void beforeJFinalStop() {
		// 系统关闭前调用
		super.beforeJFinalStop();
	}

	/**
	 * 多数据源操作
	 */
	public void mutiDsModel() {
		// 默认使用arp.addMapping（...）时关联起来的数据源
		Blog blog = Blog.dao.findById(123);
		
		// 只需要调用一次use方法即可切换到另一数据源上去
		blog.use("backupDatabase").save();
		
		//查询dsMysql数据源中的user
		List<Record> users = Db.use("mysql").find("select * from user");
		//查询dsOracle数据源中的blog
		List<Record> blogs = Db.use("oracle").find("select * from blog");
	}
	
}
