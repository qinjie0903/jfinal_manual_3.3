package demo;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.jfinal.plugin.ehcache.IDataLoader;

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
	
	
	@Before(CacheInterceptor.class)
	public void list() {
		List<Blog> blogList = Blog.dao.find("select * from blog");
		User user = User.dao.findById(getParaToInt());
		setAttr("blogList", blogList);
		setAttr("user", user);
		render("blog.html");
	}
	
	
	@Before(CacheInterceptor.class)
	@CacheName("blogList")
	public void listAnnoation() {
		List<Blog> blogList = Blog.dao.find("select * from blog");
		setAttr("blogList", blogList);
		render("blog.html");
	}
	
	@Before(EvictInterceptor.class)
	@CacheName("blogList")
	public void update() {
		getModel(Blog.class).update();
		redirect("blog.html");
	}
	
	/**
	 * CacheKit缓存操作类
	 */
	public void list1() {
		List<Blog> blogList = CacheKit.get("blog","blogList");
		if (blogList==null) {
			blogList = Blog.dao.find("select * from blog");
			CacheKit.put("blog", "blogList", blogList);
		}
		setAttr("blogList", blogList);
		render("blog.html");
	}
	
	public void list2() {
		List<Blog> blogList = CacheKit.get("blog","blogList",new IDataLoader() {
			
			@Override
			public Object load() {
				return Blog.dao.find("select * from blog");
			}
		});
		setAttr("blogList", blogList);
		render("blog.html");
	}
	
	
}
