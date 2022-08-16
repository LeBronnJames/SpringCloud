package com.wangchangyang.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RestTemplate restTemplate;

	/**
	 * @param fallbackMethod 服务降级方法
	 * @param commandKey     命令名称
	 * @param groupKey       命令分组名称
	 * @param threadPoolKey  线程池名称
	 * 
	 * @return
	 */
	@CacheResult(cacheKeyMethod = "getUserCacheKey")
	@HystrixCommand(fallbackMethod = "getUserFallback", commandKey = "getUserCommand", groupKey = "userGroup", threadPoolKey = "userServiceThread")
	public String getUser(String userId) {
		return restTemplate.getForEntity("http://SERVICE/user/getUser/{1}", String.class, userId).getBody();
	}

	public String getUserFallback(String userId) {
		return "getUserFallback,userId:" + userId + ",is Error";
	}

	@CacheRemove(commandKey = "getUserCommand", cacheKeyMethod = "getUserCacheKey")
	@HystrixCommand
	public void flushCache(String userId) {
		logger.info("flushCache,userId:{}", userId);
	}

	public String getUserCacheKey(String userId) {
		return userId;
	}

	@HystrixCommand(fallbackMethod = "postUserFallback", commandKey = "postUserCommand", groupKey = "userGroup", threadPoolKey = "userServiceThread")
	public String postUser(Map<String, String> map) {
		int StatusCode = restTemplate.postForEntity("http://SERVICE/user/postUser", map, String.class)
				.getStatusCodeValue();
		flushCache(map.get("userId"));
		return String.valueOf(StatusCode);
	}

	public String postUserFallback(Map<String, String> map, Throwable e) {
		return "postUserFallback,map:" + map.toString() + ",is Error:" + e.getMessage();
	}

}
