package com.atm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@SuppressWarnings("all")
public class HibernateDaoHelper extends HibernateDaoSupport {

	public void setHibernateSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public <T> void addEntity(T entity) {
		getHibernateTemplate().save(entity);
	}
	
	public <T> void updateEntity(T entity) {
		getHibernateTemplate().update(entity);
	}

	public <T> void saveOrUpdateEntity(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public <T> void deleteEntity(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public <T> T getEntityByKey(Class objClass, Serializable keyId)
    {
    	return (T)getHibernateTemplate().get(objClass, keyId);
    }
	
	public void deleteEntityByKey(Class objClass, Serializable keyID) {
		deleteEntity(getEntityByKey(objClass, keyID));
	}
	
	public <T> void deleteEntities(Collection<T> entities) {
		for(T entity : entities) {
			this.deleteEntity(entity);
		}
	}
	
	public int executeHQL(String hql) {
		return this.getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	public int executeHQLByPara(String hql, Object...params) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		if(params != null && params.length > 0) {
			for(int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return this.getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	//此处query.setFirstResult的参数需要测�?
	public <T> List<T> findPageBySql(String hql,int firstResult, int maxResult) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		if(firstResult == 0)
		{
			query.setFirstResult(firstResult);
		}
		else
		{
			query.setFirstResult(firstResult/maxResult+1);
		}
		
		query.setMaxResults(maxResult);
		return query.list();
	}
	
	public long getTotalBySql(String hql) {
//		long total = (long) this.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
//		return total;
		return 0;
	}
	
	public long getTotalBySql(String hql,Object...values)
	{
		long total = this.findByHql(hql, values).size();
		return total;
	}
	
	public <T> boolean contains(T entity) {
		return this.getHibernateTemplate().contains(entity);
	}
	
	public long getTotal(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 1);
		return list.get(0);
	}
	
	//此处的firstResult 为偏移量，不是第几页
	public <T> List<T> findByPage(DetachedCriteria detachedCriteria, int firstResult, int maxResult) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResult);
	}
	
	public <T> List<T> findByCriteria(DetachedCriteria detachedCriteria) {	
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	
	public List listEntities(String strHsql, String[] params, Object[] values) {
		List entities = null;
		if (null != values && values.length > 0) 
		{
			Query query = this.getSessionFactory().getCurrentSession().createQuery(strHsql);
			if (null != params || params.length > 0) 
			{
				if(values.length != params.length) 
				{
					Exception e = new Exception("参数名与参数值的个数不一致！");
					e.printStackTrace();
					return null;
				}
				else
				{
					for (int i = 0; i < values.length; i++) {
						query.setParameter(params[i], values[i]);
					}
				}
			}
			else
			{
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i + 1, values[i]);
				}
			}
			entities = query.list();
		}
		else
		{
			entities = getHibernateTemplate().find(strHsql);
		}
		return entities;
	}
	
	public <T> List<T> findByHql(String hql, Object...values) {
		return (List<T>) this.getHibernateTemplate().find(hql, values);
	}
	
}
