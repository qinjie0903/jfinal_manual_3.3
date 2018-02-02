package demo;

public class TableJoinUsage {

	public void relation() {
		String sql = "select b.*,u.user_name from blog b inner join user u on b.user_id=u.id where b.id=?";
		Blog blog = Blog.dao.findFirst(sql, 123);
		String name = blog.getSql("user_name");
	}
}
