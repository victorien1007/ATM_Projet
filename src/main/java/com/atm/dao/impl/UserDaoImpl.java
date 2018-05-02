package com.atm.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atm.dao.BaseDao;
import com.atm.dao.UserDao;
import com.atm.entity.User;
import com.atm.util.EncryptionTool;

@Repository("userDao")
@Scope(value="prototype")
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User findById(String id) {
		
		String sql = "select * from user where id=?";
		
		List<Map<String,Object>>  list = this.getJdbcDaoHelper().query(sql, id);
		
		if(list.size() == 0)
		{
			return null;
		}
		else
		{
			User user = new User();
			user.setId(id);
			user.setAddress(list.get(0).get("address").toString());
			user.setIdCard(list.get(0).get("idCard").toString());
			user.setName(list.get(0).get("name").toString());
			user.setPhone(list.get(0).get("phone").toString());
			return user;
		}
		
		
		
	}

	@Override
	public boolean isValide(String id, String password) {
		
		String sql = "select password from user where id=?";
		
		List<Map<String,Object>>  list = this.getJdbcDaoHelper().query(sql, id);
		
		if(list.size() == 0)
		{
			return false;
		}
		else
		{
			String encryptPassword = EncryptionTool.getMD5Info(password);
			if(encryptPassword.equals(list.get(0).get("password").toString()))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
	}

	@Override
	public void addUser(User user) {
		
		if(user == null)
			return;
		
		String sql = "insert into user values(?,?,?,?,?,?)";
		
		this.getJdbcDaoHelper().update(sql, user.getId(),user.getName(),
				user.getPassword(),user.getPhone(),user.getAddress(),user.getIdCard());
		
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) {
		
		String sql = "update user set address=?,password=?,phone=? where id=?";
		
		this.getJdbcDaoHelper().update(sql, user.getAddress(),user.getPassword(),user.getPhone(),user.getId());
		
	}

}
