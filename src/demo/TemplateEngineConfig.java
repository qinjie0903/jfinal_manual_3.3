package demo;

import javax.el.MapELResolver;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

public class TemplateEngineConfig extends JFinalConfig{

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
		me.setDevMode(true);
		
		me.setBaseTemplatePath(null);
		me.setSourceFactory(new ClassPathSourceFactory());
		
		me.addSharedFunction("/_view/common/__layout.html");
		me.addSharedFunction("/_view/common/_paginate.html");
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		
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
