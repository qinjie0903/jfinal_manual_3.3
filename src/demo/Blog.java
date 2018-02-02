package demo;

import com.jfinal.plugin.activerecord.Model;

public class Blog extends Model<Blog>{
	public static final Blog me = new Blog();
	public static final Blog dao = new Blog().dao();
	public User getUser() {
		return User.dao.findById(get("user_id"));
	}
	

}
