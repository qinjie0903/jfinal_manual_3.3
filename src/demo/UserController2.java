package demo;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

@Before(AAA.class)
public class UserController2 extends Controller {
	
	@Clear
	@Before(BBB.class)
	public void login() {
		//Global、Class级别的拦截器将被清除，但本方法上声明的BBB不受影响
	}
	
	@Clear({AAA.class,CCC.class}) //清除指定的拦截器AAA与CCC
	@Before(CCC.class)
	public void show() {
		// 虽然Clear注解中指定清除CCC，但她无法被清除，因为清除操作只针对于本层以上的各层
	}
}
