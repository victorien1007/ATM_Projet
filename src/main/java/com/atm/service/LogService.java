package com.atm.service;

import java.util.List;
import java.util.Map;

public interface LogService {

	public Map<String,Object> getAllLog(String userId,Integer pageNum,Integer rows,String search);
	
	public void depositLog(String srcId,String desId,String type,Double money);
	
	public void withdrawLog(String srcId,String desId,String type,Double money);
	
}
