package com.atm.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.atm.dao.AccountDao;
import com.atm.dao.LogDao;
import com.atm.entity.Account;
import com.atm.service.AccountService;

@Service("accountService")
@Scope(value="prototype")
public class AccountServiceImpl implements AccountService {

	@Resource(name="accountDao")
	private AccountDao accountDao;
	
	@Resource(name="logDao")
	private LogDao logDao;
	
	
	@Override
	public Account getById(String id) {
		return accountDao.findById(id);
	}

	@Override
	public void addAccount(Account account) {
		accountDao.addAccount(account);
	}

	@Override
	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	@Override
	public void deleteAccount(String id) {
		accountDao.deleteAccount(id);
	}

	@Override
	public boolean withdraw(String id, Double money) {
		
		Account account  = getById(id);
		if(null == account || account.getBalance()<money)
		{
			return false;
		}
		else
		{
			return accountDao.withdraw(id, money);
		}
		
	}

	@Override
	public boolean deposit(String id, Double money) {
		
		return accountDao.deposit(id, money);
	}

	@Override
	public boolean transferAccount(String srcId, Double money, String desId) {
		
		if(withdraw(srcId, money))
		{
			deposit(desId, money);
			logDao.transferLog(srcId, desId, money);
			return true;
		}
		else
		{
			return false;
		}
	}

}
