package com.atm.dao;

import com.atm.entity.Account;

public interface AccountDao {

	public Account findById(String id);
	
	public void addAccount(Account account);
	
	public void updateAccount(Account account);
	
	public void deleteAccount(String id);
	
	public boolean withdraw(String id,Double money);
	
	public boolean deposit(String id,Double money);
	
	public boolean transferAccount(String srcId,Double money,String desId);
}
