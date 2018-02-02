package demo;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User>{

	private static final long serialVersionUID = 1L;
	
	public static final User dao = new User().dao();
	
	public List<Blog> getBlogs(){
		return Blog.me.find("select * from blog where user_id=?",get("id"));
	}
	
	
	
}

