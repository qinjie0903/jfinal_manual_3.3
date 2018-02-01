package demo;

import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("/view/front");
		add("/",IndexController.class);
		add("/blog",BlogController.class);
	}

}
