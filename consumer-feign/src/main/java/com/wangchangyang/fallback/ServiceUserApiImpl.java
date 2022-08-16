package com.wangchangyang.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wangchangyang.api.ServiceUserApi;
import com.wangchangyang.pojo.User;

@Component
public class ServiceUserApiImpl implements ServiceUserApi {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public String getUser(String userId, Integer age) {
		return "======================触发熔断======================";
	}

	@Override
	public void postUserByPojo(User user) {
		logger.info("======================触发熔断======================");
	}
	
}
