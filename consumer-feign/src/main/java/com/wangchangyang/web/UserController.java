package com.wangchangyang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangchangyang.api.ServiceUserApi;
import com.wangchangyang.pojo.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ServiceUserApi serviceUserApi;
	
	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	public String getUser(@RequestParam String userId,@RequestParam(required = false) Integer age) {
		return serviceUserApi.getUser(userId,age);
	}
	
	@RequestMapping(value = "/postUser", method = RequestMethod.POST)
	public void postUser(@RequestBody User user) {
		serviceUserApi.postUserByPojo(user);
	}
	
	
}
