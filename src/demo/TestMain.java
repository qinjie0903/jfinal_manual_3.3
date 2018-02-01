package demo;

import com.jfinal.aop.Duang;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.tx.Tx;

public class TestMain {

	public static void main(String[] args) {
		//使用Duang.duang方法在任何地方对目标进行增强
		OrderService service = Duang.duang(OrderService.class);
		
		// 调用payment方法时将会触发拦截器
		service.payment(0,0);
		
		//使用Enhancer.enhance方法在任何地方对目标进行增强
		OrderService service2 = Enhancer.enhance(OrderService.class);
	}
	
	public void injectDemo() {
		//为enhance方法传入的拦截器称为Inject拦截器，下面代码中的Tx称为Inject拦截器
		OrderService service = Enhancer.enhance(OrderService.class,Tx.class);
		service.payment(0, 0);
	}
	
}
