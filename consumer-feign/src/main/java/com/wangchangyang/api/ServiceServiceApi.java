package com.wangchangyang.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wangchangyang.fallback.ServiceServiceApiImpl;

@FeignClient(name = "service", contextId = "service-service", path = "/service", fallback = ServiceServiceApiImpl.class)
public interface ServiceServiceApi {

	@RequestMapping(value = "/instance", method = RequestMethod.GET)
	public String instance();

}
