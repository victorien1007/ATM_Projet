package com.atm.service;

import com.atm.entity.User;

public interface UserService {


	public User getById(String id);
	
	public boolean isValide(String id,String password);
	
	public void addUser(User user);
	
	public void deleteUser(String id);
	
	public void updateUser(User user);
}
