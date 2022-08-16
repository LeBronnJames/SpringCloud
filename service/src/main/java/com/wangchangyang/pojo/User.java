package com.wangchangyang.pojo;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userName;
	
	private User() {};
	
	private static User userInstance = new User();
	
	public static User instance () {
		return userInstance;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "User,userId:" + userId + ",userName:" + userName;
	}
	
}
