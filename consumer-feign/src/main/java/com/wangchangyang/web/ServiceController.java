package com.wangchangyang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangchangyang.api.ServiceServiceApi;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	ServiceServiceApi serviceServiceApi;
	
	@RequestMapping(value = "/instance", method = RequestMethod.GET)
	public String instance() {
		return serviceServiceApi.instance();
	};

	
}
