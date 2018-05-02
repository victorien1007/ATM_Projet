package com.atm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.atm.dao.AccountDao;
import com.atm.dao.LogDao;
import com.atm.service.LogService;


@Service("logService")
@Scope(value="prototype")
@SuppressWarnings("all")
public class LogServiceImpl implements LogService {

	@Resource(name="logDao")
	private LogDao logDao;
	

	@Override
	public void depositLog(String srcId, String desId, String type, Double money) {
		logDao.depositLog(srcId, desId, type, money);
	}


	@Override
	public void withdrawLog(String srcId, String desId, String type, Double money) {
		logDao.withdrawLog(srcId, desId, type, money);
	}


	@Override
	public Map<String,Object> getAllLog(String userId, Integer offset, Integer limit, String search) {

		return logDao.findAllLog(userId, offset, limit, search);
	}

}
