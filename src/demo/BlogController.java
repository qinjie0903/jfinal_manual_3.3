package demo;

import com.jfinal.core.Controller;

public class BlogController extends Controller {
	public void save() {
		//页面的modelName正好是Blog类名的首字母小写
		Blog blog = getModel(Blog.class);
		
		// 如果表单域的名称为 "otherName.title"可加上一个参数来获取
		blog = getModel(Blog.class,"othername");
	}
}
