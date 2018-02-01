package demo;

import com.jfinal.config.Routes;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("/view/admin");
		addInterceptor(new AdminInterceptor());
		add("/admin", AdminController.class);
		add("/admin/user",UserController.class);
	}

}
