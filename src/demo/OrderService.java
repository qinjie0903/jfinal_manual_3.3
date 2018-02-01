package demo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;

// 定义需要使用AOP的业务层类
public class OrderService {
	// 配置事务拦截器
	@Before(Tx.class)
	public void payment(int orderId,int userId) {
		// service code here
	}
	
	// 定义控制器，控制器提供了enhance系列方法可对目标进行AOP增强
	public class OrderController extends Controller{
		public void payment() {
			//使用 enhance方法对业务层进行增强，使其具有AOP能力
			OrderService service = enhance(OrderService.class);
			
			// 调用payment方法时将会触发拦截器
			service.payment(getParaToInt("orderId"), getParaToInt("userId"));
			
		}
	}
}
