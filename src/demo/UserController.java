package demo;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

//login方法需要移除该权限拦截器才能正常登录

@Before(AuthInterceptor.class)
public class UserController extends Controller {
	
	//ActionKey注解
	@ActionKey("/login")
	public void login00() {
		render("login.html");
	}
	
	//AuthInterceptor 已被Clear清除掉，不会被其拦截
	@Clear
	public void login() {}
	
	//此方法将被AuthInterceptor拦截
	public void show() {}
}
