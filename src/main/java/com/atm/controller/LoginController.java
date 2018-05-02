package com.atm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atm.entity.User;
import com.atm.service.UserService;
import com.atm.util.EncryptionTool;
import com.atm.util.JsonTool;

@Controller
@Scope(value="prototype")
@RequestMapping("/atm")
public class LoginController {

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value="/login/{id}/{password}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String handlLogin(@PathVariable("id") String id,
			@PathVariable("password") String password,HttpServletRequest request)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(userService.isValide(id, password))
		{
			HttpSession session = request.getSession();
			session.setAttribute("userId", id);
			map.put("code", 1);
			map.put("data", "/atm/index");
		}
		else
		{
			map.put("code", 0);
			map.put("data", "用户名或密码错误");
		}
		
		return JsonTool.Obj2Json(map);
	}
	
	@RequestMapping(value="/register/{id}/{name}/{password}/{phone}/{address}/{idCard}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String handlRegister(@PathVariable("id") String id,
			@PathVariable("name") String name,
			@PathVariable("password") String password,
			@PathVariable("phone") String phone,
			@PathVariable("address") String address,
			@PathVariable("idCard") String idCard,HttpServletRequest request)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		
		User oldUser = userService.getById(id);
		
		if(oldUser == null)//此id可以使用
		{
			User user = new User();
			user.setId(id);
			user.setAddress(address);
			user.setIdCard(idCard);
			user.setName(name);
			user.setPhone(phone);
			
			String md5Password = EncryptionTool.getMD5Info(password);
			
			user.setPassword(md5Password);
			
			userService.addUser(user);
			
			map.put("code", 1);
			map.put("data", "/atm/index");
		}
		else
		{
			map.put("code", 0);
			map.put("data", "用户名或密码错误");
		}
		
		return JsonTool.Obj2Json(map);
	}
	
	
	@RequestMapping("/logout")
	public String logout(){
		return "redirect:/";
	}
	
	
	
}
