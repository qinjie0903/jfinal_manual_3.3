package demo;

import com.jfinal.plugin.activerecord.Model;

public class Article extends Model<Article>{
	
	
	private static final long serialVersionUID = 1L;
	//声明的dao静态对象是为了方便查询操作而定义的，该对象并不必须。 
	//无需定义属性，无需定义setter和getter方法，无需XML配置，无需Annotation配置
	public static final Article dao = new Article().dao();

}
