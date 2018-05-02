package com.atm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atm.service.AccountService;
import com.atm.service.LogService;
import com.atm.service.UserService;
import com.atm.util.JsonTool;

@Controller
@Scope(value="prototype")
@RequestMapping("/atm")
public class LogController {

	@Resource(name="logService")
	private LogService logService;
	
	
	/**
	 * 	//显示列表的时候需要前端发送有个orgId和选中树的deptId
	@RequestMapping("/list/{orgId}/{deptId}")
	@ResponseBody
	public String getAll(HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @PathVariable("orgId") String orgId,
            @PathVariable("deptId") String deptId) throws Exception
	 * 日志
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/log/{id}/{search}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String handlDeposit(@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @PathVariable("id") String id,
            @PathVariable("search") String search)
	{
		Map<String,Object> map = new HashMap<String, Object>();
		
		Map<String,Object> dataMap = logService.getAllLog(id,offset,limit,search);
		
		map.put("total", Integer.valueOf(dataMap.get("total").toString()));
		map.put("rows", dataMap.get("data"));
		
		return JsonTool.Obj2Json(map);
	}
	
	
}
