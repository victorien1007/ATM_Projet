package com.atm.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atm.entity.Account;
import com.atm.entity.User;
import com.atm.service.AccountService;
import com.atm.service.LogService;
import com.atm.service.UserService;
import com.atm.util.JsonTool;

@RestController
@Scope(value = "prototype")
public class TestController {

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="accountService")
	private AccountService accountService;
	
	@Resource(name="logService")
	private LogService logService;

	
	@RequestMapping(value="/getUserById/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String handleById(@PathVariable("id") String id)
	
	{
		String result = "";
		User user = userService.getById(id);
		result = JsonTool.Obj2Json(user);
		return result;
	}
	
	@RequestMapping(value="/getAccountById/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String handleAccountId(@PathVariable("id") String id)
	
	{
		String result = "";
		Account account = accountService.getById(id);
		result = JsonTool.Obj2Json(account);
		return result;
	}
	
	@RequestMapping(value="/depositById/{id}/{money}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String handleDeposit(@PathVariable("id") String id,
			@PathVariable("money") Double money)
	
	{
		String result = "";
		accountService.deposit(id, money);
		Account account = accountService.getById(id);
		result = JsonTool.Obj2Json(account);
		return result;
	}
	
	@RequestMapping(value="/withdrawById/{id}/{money}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String handleWithdraw(@PathVariable("id") String id,
			@PathVariable("money") Double money)
	
	{
		String result = "";
		accountService.withdraw(id, money);
		Account account = accountService.getById(id);
		result = JsonTool.Obj2Json(account);
		return result;
	}
	
	@RequestMapping(value="/transfer/{srcId}/{money}/{desId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String handleTransfer(@PathVariable("srcId") String srcId,
			@PathVariable("money") Double money,
			@PathVariable("desId") String desId)
	
	{
		String result = "";
		accountService.transferAccount(srcId, money, desId);
		Account account = accountService.getById(srcId);
		result = JsonTool.Obj2Json(account);
		return result;
	}
	

//	@RequestMapping(value="/getLogById/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String handleLog(@PathVariable("id") String id)
//	
//	{
//		String result = "";
//	
//		result = logService.getAllLog(id);
//		
//		return result;
//	}
}
