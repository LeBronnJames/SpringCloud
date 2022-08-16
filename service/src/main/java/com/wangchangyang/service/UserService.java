package com.wangchangyang.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wangchangyang.pojo.User;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public String getUser(String userId) {
		if (userId.equals(User.instance().getUserId())) {
			return User.instance().toString();
		}
		return "getUser,Empty";
	}
	
	public String getUser(String userId,Integer age) {
		if (userId.equals(User.instance().getUserId())) {
			return User.instance().toString() + ",age:" + age;
		}
		return "getUser,Empty";
	}

	public void postUser(Map<String, String> map) {
		User u = User.instance();
		u.setUserId(map.get("userId"));
		u.setUserName(map.get("userName"));
		logger.info("postUser by map:{}",map.toString());
	}
	
	public void postUser(User user) {
		User u = User.instance();
		u.setUserId(user.getUserId());
		u.setUserName(user.getUserName());
		logger.info("postUser by user:{}",user.toString());
	}

	public List<String> getUsers(String userIds) {
		List<String> results = new ArrayList<String>();
		List<String> userIdsList = Arrays.asList(StringUtils.split(userIds, ","));
		for (String userId : userIdsList) {
			results.add(getUser(userId));
		}
		return results;
	}

}
