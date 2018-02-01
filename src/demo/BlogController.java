package demo;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

//配置一个Class级别的拦截器，她将拦截本类中的所有方法

@Before(AaaInter.class)
public class BlogController extends Controller {
	
	//配置多个Method级别的拦截器，仅拦截本方法
	@Before({BbbInter.class , CccInter.class})
	public void index() {
		
	}
	
	//未配置Method级别拦截器，但会被Class级别拦截器AaaInter所拦截
	public void show() {
		
	}
	
	public void save() {
		//页面的modelName正好是Blog类名的首字母小写
		Blog blog = getModel(Blog.class);
		
		// 如果表单域的名称为 "otherName.title"可加上一个参数来获取
		blog = getModel(Blog.class,"othername");
	}
	
	
	
	
	
}
