package com.smart.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.smart.domain.hibernate.LoginLog;

@Repository
public class LoginLogHibernateDao extends BaseDao {

	public void addLoginLog(LoginLog loginlog) {
		getHibernateTemplate().save(loginlog);
	}

}
