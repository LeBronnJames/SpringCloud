package com.wangchangyang.fallback;

import org.springframework.stereotype.Component;

import com.wangchangyang.api.ServiceServiceApi;

@Component
public class ServiceServiceApiImpl implements ServiceServiceApi {

	@Override
	public String instance() {
		return "======================触发熔断======================";
	}

}
