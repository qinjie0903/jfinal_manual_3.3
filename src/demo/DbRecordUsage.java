package demo;

import java.sql.SQLException;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class DbRecordUsage {

	public void usage() {
		// 创建name属性为James,age属性为25的record对象并添加到数据库
		Record user = new Record().set("name", "James").set("age", 25);
		Db.save("user",user);
		
		// 删除id值为25的user表中的记录
		Db.deleteById("user",25);
		
		// 查询id值为25的Record将其name属性改为James并更新到数据库
		user = Db.findById("user", 25).set("name", "James");
		Db.update("user",user);
		
		// 获取user的name属性
		String userName = user.getStr("name");
		
		// 获取user的age属性
		Integer userAge =user.getInt("age");
		
		// 查询所有年龄大于18岁的user
		List<Record> users = Db.find("select * from user where age > 18");
		 
		// 分页查询年龄大于18的user,当前页号为1,每页10个user
		Page<Record> userPage = Db.paginate(1, 10, "select *"," from user where age > ?",18);
		
		// group by 在最外层时使用该方法，再内层时时有上面的方法
		Db.paginate(1, 10, true, "select *", "from girl where age > ? group by age", 18);

		// 
		String from = "from girl where age > ?";
		String totalRowSql = "select count(*) "+ from ;
		String findSql = "select * "+ from +"order by age";
		
		User.dao.paginateByFullSql(1, 10, totalRowSql, findSql, 18);
		
		// 以下数据库更新操作在一个事务中执行，如果发生异常或者返回false，则回滚
		boolean succeed = Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				int count = Db.update("update account set cash=cash-? where id=?",100,123);
				
				int count2 = Db.update("update account set cash=cash+? where id=?",100,456);
			
				return count==1&&count2==1;

			}
		});
		
		
		// 使用Db+Record模式，使用符合主键则无需配置
		Db.findById("user_role", "roleId,userId",123,456);
		Db.deleteById("user_role", "roleId,userId",123,456);
		
		
		
		// Oracle 支持，需先创建序列
		/*CREATE SEQUENCE MY_SEQ
		INCREMENT BY 1
		MINVALUE 1
		MAXVALUE 99999999999
		START WITH 1
		CACHE 20;*/
 		// 创建User并使用序列
		User user2 = new User().set("id", "MY_SEQ.nextval").set("age", 18);
		user2.save();
		// 获取id值
		Integer id = user2.get("id");
		
	}
	
}
