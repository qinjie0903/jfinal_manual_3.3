package demo;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

public class UserModelUsage {

	public void usage() {

		// 创建name属性为James,age属性为25的User对象并添加到数据库
		new User().set("name", "James").set("age", 28).save();

		// 删除id值为25的User
		User.dao.deleteById(26);

		// 查询id值为25的User将其name属性改为James并更新到数据库
		User.dao.findById(27).set("name", "James").update();

		// 查询id值为25的user, 且仅仅取name与age两个字段的值
		User user = User.dao.findByIdLoadColumns(26, "name,age");

		// 获取user的name属性
		String userName = user.getStr("name");

		// 获取user的age属性
		Integer userAge = user.getInt("age");

		// 查询所有年龄大于18岁的user
		List<User> users = User.dao.find("select * from user where age >18");

		// 分页查询年龄大于18的user,当前页号为1,每页10个user
		Page<User> userPage = User.dao.paginate(1, 10, "select *", "from user where age > ?", 18);

	}
}
