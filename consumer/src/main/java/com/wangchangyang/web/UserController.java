package com.wangchangyang.web;

import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangchangyang.collapser.UserBatchService;
import com.wangchangyang.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserBatchService userBatchService;

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String getUser(@RequestParam String userId) {
		String userResult = userService.getUser(userId);
		return userResult;

	}

	@RequestMapping(value = "/postUser", method = RequestMethod.POST)
	public String postUser(@RequestBody Map<String, String> map) {
		return userService.postUser(map);
	}

	@RequestMapping(value = "/getUserCollapser", method = RequestMethod.GET)
	public String getUserCollapser(@RequestParam String userId) throws Exception {
		Future<String> userResult = userBatchService.getUserCollapser(userId);
		return userResult.get();

	}
	
	@RequestMapping(value = "/getUserCollapserForTest", method = RequestMethod.GET)
	public void getUserCollapser() throws Exception {
		Future<String> userResult1 = userBatchService.getUserCollapser("111");
		Future<String> userResult2 = userBatchService.getUserCollapser("222");
		Future<String> userResult3 = userBatchService.getUserCollapser("333");
		Thread.sleep(1000);
		Future<String> userResult4 = userBatchService.getUserCollapser("444");
		logger.info(userResult1.get());
		logger.info(userResult2.get());
		logger.info(userResult3.get());
		logger.info(userResult4.get());
	}

}
