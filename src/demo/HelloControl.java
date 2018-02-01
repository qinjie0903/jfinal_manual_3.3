package demo;

import com.jfinal.core.Controller;

public class HelloControl extends Controller{
	
	//Action修饰符必须是public
	public void index() {
		renderText("此方法是一个Action");
	}
	
	//Action可以有返回值
	public String test() {
		return "index.html";
	}
}
