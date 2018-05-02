package com.atm.dao;

import com.atm.entity.User;

public interface UserDao {

	public User findById(String id);
	
	public boolean isValide(String id,String password);
	
	public void addUser(User user);
	
	public void deleteUser(String id);
	
	public void updateUser(User user);
	
}
