package com.atm.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcDaoHelper extends JdbcDaoSupport{
	
	public void setJdbcDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	@SuppressWarnings("rawtypes")
	public List query(String sql){
		
		List<Map<String,Object>> lists = getJdbcTemplate().queryForList(sql);
		
		for (Map<String, Object> map : lists) {
			
			System.out.println(map.keySet());
			System.out.println(map.values());
			
		}
		
		return getJdbcTemplate().queryForList(sql);
	}
	
	public List query(String sql, Object...args)
	{
		return getJdbcTemplate().queryForList(sql, args);
	}
	
	public int update(String sql, Object...args) {
		
		return getJdbcTemplate().update(sql, args);
	}
}
