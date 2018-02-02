package demo;

import java.util.List;

import com.jfinal.core.Controller;

public class CacheUsage extends Controller{
	
	public void list() {
		List<Blog> blogList = Blog.dao.findByCache("cacheName", "key", "select * from blog");
		setAttr("blogList",blogList).render("list.html");
	}
}
