package com.atm.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.atm.dao.UserDao;
import com.atm.entity.User;
import com.atm.service.UserService;

@Service("userService")
@Scope(value="prototype")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public User getById(String id) {
		return userDao.findById(id);
	}

	@Override
	public boolean isValide(String id, String password) {
		return userDao.isValide(id, password);
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public void deleteUser(String id) {
		userDao.deleteUser(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

}
