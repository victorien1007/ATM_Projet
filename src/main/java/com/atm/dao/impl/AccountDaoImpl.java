package com.atm.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atm.dao.AccountDao;
import com.atm.dao.BaseDao;
import com.atm.entity.Account;
import com.atm.entity.User;

@Repository("accountDao")
@Scope(value="prototype")
public class AccountDaoImpl extends BaseDao implements AccountDao {

	@Override
	public Account findById(String id) {
		
		String sql = "select * from account where user_id=?";
		
		List<Map<String,Object>>  list = this.getJdbcDaoHelper().query(sql, id);
		
		if(list.size() == 0)
		{
			return null;
		}
		else
		{
			Account account = new Account();
			
			account.setId(id);
			account.setUserId(list.get(0).get("user_id").toString());
			account.setType(list.get(0).get("type").toString());
			account.setBalance(Double.valueOf(list.get(0).get("balance").toString()));
			
			return account;
		}
		
		
	}

	@Override
	public void addAccount(Account account) {
		if(account == null)
			return;
		
		String sql = "insert into account values(?,?,?,?)";
		
		this.getJdbcDaoHelper().update(sql, account.getId(),account.getUserId(),account.getBalance(),account.getType());
	}

	@Override
	public void updateAccount(Account account) {

	}

	@Override
	public void deleteAccount(String id) {

	}

	@Override
	public boolean withdraw(String id, Double money) {
		
		String sql = "update account set balance = balance-? where user_id = ?";
		
		int result = this.getJdbcDaoHelper().update(sql, money,id);
		
		if(0 == result)
		{
			return false;
		}
		else
		{
			
			return true;
		}
		
	}

	@Override
	public boolean deposit(String id, Double money) {
		
		String sql = "update account set balance = balance+? where user_id = ?";
		
		int result = this.getJdbcDaoHelper().update(sql, money,id);
		
		if(0 == result)
		{
			return false;
		}
		
		else
		{
			//写存款日志
			//String sqlLog = "insert into log (srcId,desId,type,money,date) values (?,?,?,?,?)";
			//this.getJdbcDaoHelper().update(sqlLog, id,"","存款",money,new Date());
			return true;
		}
	}

	@Override
	public boolean transferAccount(String srcId, Double money, String desId) {
		
		
		
		
		return true;
	}

}
