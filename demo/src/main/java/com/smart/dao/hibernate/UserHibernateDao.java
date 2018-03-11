package com.smart.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.smart.domain.hibernate.User;

@Repository
public class UserHibernateDao extends BaseDao {

	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}

	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	public User getUser(int userId) {
		return getHibernateTemplate().get(User.class, userId);
	}

	public long getUserNum() {
		Object obj = getHibernateTemplate().iterate(
				"select count(u.userId) from User u").next();
		return (Long) obj;
	}

	public long getUserNum2() {
		Long userNum = getHibernateTemplate().execute(
				new HibernateCallback<Long>() {
					public Long doInHibernate(Session session)
							throws HibernateException{
						Object obj = session.createQuery("select count(u.userId) from User u")
								.list()
								.iterator()
								.next();
						return (Long) obj;
					}
				});
		return userNum;
	}

	public List<User> findUserByName(String userName) {
		return (List<User>) getHibernateTemplate().find(
				"from User u where u.userName like ?", userName + "%");
	}
}
