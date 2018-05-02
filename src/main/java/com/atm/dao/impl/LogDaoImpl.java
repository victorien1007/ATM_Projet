package com.atm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atm.dao.BaseDao;
import com.atm.dao.LogDao;
import com.atm.entity.Log;
import com.atm.util.JsonTool;

@Repository("logDao")
@Scope(value="prototype")
public class LogDaoImpl extends BaseDao implements LogDao {

	public static SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Map<String,Object> findAllLog(String userId, Integer offset, Integer limit, String search) {
		
		String sql = "select * from log where (srcId = ? or desId = ?)  ";
		
		String sql2 =  "and (srcId like '%"+search+"%' or desId like '%"+search+"%' or type like '%"+search+"%' or money like '%"+search+"%' )";
		
		if(null == search || "null".equals(search))
		{
			
		}
		else
		{
			sql += sql2;
		}
		
		List<Map<String,Object>> lists = this.getJdbcDaoHelper().query(sql,userId,userId);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("total", lists.size());
		
		int allPageNum = lists.size()/limit;
		
		if(0 != lists.size()%limit)
		{
			allPageNum++;
		}
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		int length = offset+limit;
		
		if(length > lists.size())
		{
			length = lists.size();
		}
		for(int i=offset; i<length; i++)
		{
			dataList.add(lists.get(i));
		}
		
		map.put("data", dataList);
		
		return map;
	}

	@Override
	public void transferLog(String srcId, String desId, Double money) {
			//写转账日志
			String sqlLog = "insert into log (srcId,desId,type,money,date) values (?,?,?,?,?)";
			this.getJdbcDaoHelper().update(sqlLog, srcId,desId,"转账",money,sdf.format(new Date()));
	}

	@Override
	public void depositLog(String srcId, String desId, String type, Double money) {
		String sqlLog = "insert into log (srcId,desId,type,money,date) values (?,?,?,?,?)";
		this.getJdbcDaoHelper().update(sqlLog, srcId,"","存款",money,sdf.format(new Date()));
		
	}

	@Override
	public void withdrawLog(String srcId, String desId, String type, Double money) {
		String sqlLog = "insert into log (srcId,desId,type,money,date) values (?,?,?,?,?)";
		this.getJdbcDaoHelper().update(sqlLog, srcId,"","取款",money,sdf.format(new Date()));
	}

}
