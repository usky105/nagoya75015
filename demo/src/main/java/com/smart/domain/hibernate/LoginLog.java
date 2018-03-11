package com.smart.domain.hibernate;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


@Entity
@Table(name="T_LOGIN_LOG")
public class LoginLog implements Serializable{
	
	@Id
	@Column(name = "LOGIN_LOG_ID")
	private int loginLogId;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "IP")
	private String ip;
	
	@Column(name = "LOGIN_DATETIME")
	private Date loginDate;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public int getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(int loginLogId) {
		this.loginLogId = loginLogId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
