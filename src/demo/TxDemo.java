package demo;

import com.jfinal.aop.Before;
import com.jfinal.config.Interceptors;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

public class TxDemo extends Controller{

	@Before(Tx.class)
	public void tran_demo() {
		// 获取转账金额
		Integer transAmount = getParaToInt("transAmount");
				
		// 获取转出账户id
		Integer fromAccountId = getParaToInt("fromAccountId");
		
		// 获取转入账户id
		Integer toAccountId = getParaToInt("toAccountId");
		
		// 转出操作
		Db.update("update account set cash=cash-? where id=?",transAmount,fromAccountId);
		
		// 转入操作
		Db.update("update account set cash=cash+? where id=?",transAmount,toAccountId);
	}
	
	
	public void configInterceptor(Interceptors me) {
		
		
	}
}
