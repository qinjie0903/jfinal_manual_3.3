package demo;

import com.jfinal.core.Controller;

public class HelloControl extends Controller{
	public void index() {
		renderText("Hello JFinal");
	}
}
