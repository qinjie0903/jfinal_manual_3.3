package demo;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class UserController extends Controller {
	
	//ActionKey注解
	@ActionKey("/login")
	public void login() {
		render("login.html");
	}
	
}
