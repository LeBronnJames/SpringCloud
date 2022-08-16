package com.wangchangyang.web;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangchangyang.pojo.User;
import com.wangchangyang.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public String getUser(@PathVariable(name = "userId", required = true) String userId) {
		return userService.getUser(userId);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String getUser(@RequestHeader(value = "userId", required = true) String userId,
			@RequestHeader(value = "age" ,defaultValue = "100") Integer age) throws Exception {
		// 测试超时
		int sleepTime = new Random().nextInt(200);
		logger.info("getUser,sleepTime:" + sleepTime);
		Thread.sleep(sleepTime);
		return userService.getUser(userId, age);
	}

	@RequestMapping(value = "/postUser", method = RequestMethod.POST)
	public void postUser(@RequestBody Map<String, String> map) {
		userService.postUser(map);
	}

	@RequestMapping(value = "/postUserByPojo", method = RequestMethod.POST)
	public void postUserByPojo(@RequestBody User user) {
		userService.postUser(user);
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<String> getUsers(@RequestParam String userIds) {
		return userService.getUsers(userIds);
	}

}
