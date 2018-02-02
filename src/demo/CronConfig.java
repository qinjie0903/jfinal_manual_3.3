package demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.template.Engine;

public class CronConfig extends JFinalConfig{

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
		Cron4jPlugin cp= new Cron4jPlugin();
		
		cp = new Cron4jPlugin("config.txt");
		cp = new Cron4jPlugin("config.txt","cron4j");
		
		cp = new Cron4jPlugin(PropKit.use("config.txt"));
		cp = new Cron4jPlugin(PropKit.use("config.txt"), "cron4j");
		
		//cp.addTask("* * * * *", new MyTask());
		//me.add(cp);
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
