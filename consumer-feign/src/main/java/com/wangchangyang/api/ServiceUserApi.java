package com.wangchangyang.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wangchangyang.fallback.ServiceUserApiImpl;
import com.wangchangyang.pojo.User;

@FeignClient(name = "service", contextId = "service-user", path = "/user", fallback = ServiceUserApiImpl.class)
public interface ServiceUserApi {

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String getUser(@RequestHeader(value = "userId", required = true) String userId,
			@RequestHeader(value = "age") Integer age);

	@RequestMapping(value = "/postUserByPojo", method = RequestMethod.POST)
	public void postUserByPojo(@RequestBody User user);

}
