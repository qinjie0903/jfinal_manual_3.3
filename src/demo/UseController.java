package demo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class UseController extends Controller{
	//配置方式与拦截器完全一样
	@Before(LoginValidator.class)
	public void login() {
		
	}
}
