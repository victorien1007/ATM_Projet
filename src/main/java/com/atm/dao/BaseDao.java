package com.atm.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@SuppressWarnings("all")
public class BaseDao{
	/* */
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	//hibernate 助手对象
	private  HibernateDaoHelper hibernateHelper;
	
	@Autowired
	private DataSource dataSource;
	//Jdbc 助手对象
	private  JdbcDaoHelper jdbcHelper;
	
	public BaseDao(){}
	
	@PostConstruct
	private void initHibernateDaoHelper(){
		this.hibernateHelper = new HibernateDaoHelper();
		this.hibernateHelper.setHibernateSessionFactory(sessionFactory);
	}
	
	@PostConstruct
	private void initJdbcDaoHelper(){
		this.jdbcHelper = new JdbcDaoHelper();
		this.jdbcHelper.setJdbcDataSource(dataSource);
	}
	
	public HibernateDaoHelper getHibernateDaoHelper(){
		return  this.hibernateHelper;
	}
	
	public JdbcDaoHelper getJdbcDaoHelper(){
		return  this.jdbcHelper;
	}

}
