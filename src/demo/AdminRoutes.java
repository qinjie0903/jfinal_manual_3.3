package demo;

import com.jfinal.config.Routes;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		
		addInterceptor(new AdminAuthInterceptor());
		add("/admin", IndexAdminController.class,"/index");
		add("/admin/project",ProjectAdminController.class,"/project");
		add("/admin/share",ShareAdminController.class,"/share");
		
		
		/*
		setBaseViewPath("/view/admin");
		addInterceptor(new AdminInterceptor());
		add("/admin", AdminController.class);
		add("/admin/user",UserController.class);*/
	}

}
