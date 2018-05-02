package com.atm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atm.entity.Log;

public interface LogDao {

	public Map<String,Object> findAllLog(String userId,Integer pageNum,Integer rows,String search);
	
	public void transferLog(String srcId,String desId,Double money);
	
	public void depositLog(String srcId,String desId,String type,Double money);
	
	public void withdrawLog(String srcId,String desId,String type,Double money);
	
	/*
	 * //дȡ����־
			String sqlLog = "insert into log (srcId,desId,type,money,date) values (?,?,?,?,?)";
			this.getJdbcDaoHelper().update(sqlLog, id,"","ȡ��",money,new Date());
	 */
}
