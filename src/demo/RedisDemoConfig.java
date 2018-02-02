package demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;

public class RedisDemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// 用于缓存bbs模块的redis服务
		RedisPlugin bbsRedis = new RedisPlugin("bbs", "localhost");
		me.add(bbsRedis);
		
		// 用于缓存news模块的redis服务
		RedisPlugin newsRedis = new RedisPlugin("newa", "192.168.1.1");
		me.add(newsRedis);
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
