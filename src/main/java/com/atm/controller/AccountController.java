package com.atm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atm.entity.Account;
import com.atm.entity.User;
import com.atm.service.AccountService;
import com.atm.service.LogService;
import com.atm.service.UserService;
import com.atm.util.EncryptionTool;
import com.atm.util.JsonTool;

@Controller
@Scope(value="prototype")
@RequestMapping("/atm")
public class AccountController {

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="accountService")
	private AccountService accountService;
	
	@Resource(name="logService")
	private LogService logService;
	
	
	/**
	 * 余额
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/balance/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String handlDeposit(@PathVariable("id") String id)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		
		Account account = accountService.getById(id);
		
		map.put("code", 1);
		map.put("balance", account.getBalance());
		
		return JsonTool.Obj2Json(map);
	}
	
	
	
	/**
	 * 存款
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/deposit/{id}/{money}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String handlDeposit(@PathVariable("id") String id,
			@PathVariable("money") Double money)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		
		Account oldAccount = accountService.getById(id);
		if (null == oldAccount) {
			Account account = new Account();
			account.setId(EncryptionTool.getMD5Info(id));
			account.setBalance(money);
			account.setType("活期存款");
			account.setUserId(id);
			accountService.addAccount(account);
		}
		else {
			accountService.deposit(id, money);
		}
	
		
		logService.depositLog(id, "", "", money);
		
		map.put("code", 1);
		map.put("data", "/atm/index");
		
		return JsonTool.Obj2Json(map);
	}
	
	/**
	 * 取款
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/withdraw/{id}/{money}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String handlWithdraw(@PathVariable("id") String id,
			@PathVariable("money") Double money)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(accountService.withdraw(id, money))
		{
			map.put("code", 1);
			map.put("data", "/atm/index");
			logService.withdrawLog(id, "", "", money);
		}
		else
		{
			map.put("code", 0);
			map.put("data", "账户余额不足");
		}
		
		return JsonTool.Obj2Json(map);
	}
	
	/**
	 * 转账
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/transfer/{srcId}/{desId}/{name}/{money}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String handlTransfer(@PathVariable("srcId") String srcId,
			@PathVariable("money") Double money,
			@PathVariable("desId") String desId,
			@PathVariable("name") String name)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		
		User user = userService.getById(desId);
		boolean isValide = false;
		if(user != null)
		{
			if(name.equals(user.getName()))
			{
				isValide = true;
				
			}
		}
		
		if(!isValide)
		{
			map.put("code", 0);
			map.put("data", "转账失败");
		}
		
		else
		{
			if(accountService.transferAccount(srcId, money, desId))
			{
				map.put("code", 1);
				map.put("data", "/atm/index");
			}
			else
			{
				map.put("code", 0);
				map.put("data", "转账失败");
			}
		}
		
		
		return JsonTool.Obj2Json(map);
	}
	
	
	
}
