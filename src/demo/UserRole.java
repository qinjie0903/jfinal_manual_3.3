package demo;

import com.jfinal.plugin.activerecord.Model;

public class UserRole extends Model<UserRole> {
	private static final long serialVersionUID = 1L;
	public static final UserRole dao = new UserRole().dao();

}
