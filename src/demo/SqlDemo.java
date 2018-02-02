package demo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.SqlPara;

public class SqlDemo {

	public void demo() {
		String sql = Db.getSql("findPrettyGirl");
		Db.find(sql,16,23);
		//String sql2 = Model.getSql("findPrettyGirl");
		//Db.use().getSql(sql);<=>Model.getSql(sql);
	
		SqlPara sqlPara = Db.getSqlPara("findPrettyGirlParaInt", 18,50);
		Db.find(sqlPara);
		
		Kv cond = Kv.by("age", 18).set("weight",50);
		SqlPara sqlPara2 = Db.getSqlPara("findPrettyGirlParaT", cond);
		Db.find(sqlPara2);
		//分页用法
		Db.paginate(1, 10, sqlPara2);
		
		//namespace使用
		String sql3 = Db.getSql("japan.findPrettyGirl");

		//高级用法
		Kv cond2 = Kv.by("age >", 16).set("age <",23).set("sex = ","female");
		SqlPara sp = Db.getSqlPara("japan.find", Kv.by("cond", cond2));
		Db.find(sp);
		
		List<Object> idList = Arrays.asList(1,2,3);
		SqlPara sp2 = Db.getSqlPara("deleteUsers", Kv.by("idList", idList));
		Db.update(sp2.getSql());
	}
}
