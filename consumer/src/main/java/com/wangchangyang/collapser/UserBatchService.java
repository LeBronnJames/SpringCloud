package com.wangchangyang.collapser;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCollapser.Scope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserBatchService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RestTemplate restTemplate;

	@HystrixCollapser(batchMethod = "getUsers", scope = Scope.GLOBAL, collapserProperties = {
			@HystrixProperty(name = "timerDelayInMilliseconds", value = "100") })
	public Future<String> getUserCollapser(String userId) {
		throw new RuntimeException("This method body should not be executed");
	}

	@HystrixCommand
	public List<String> getUsers(List<String> userIds) {
		logger.info("getUsers,userIds.size:" + userIds.size());
		logger.info("getUsers,userIds:" + userIds);
		String[] userArr = restTemplate.getForObject("http://SERVICE/user/getUsers?userIds={1}", String[].class,
				StringUtils.join(userIds, ","));
		logger.info("getUsers,userArr.length:" + userArr.length);
		return Arrays.asList(userArr);
	}

}
