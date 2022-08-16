package com.wangchangyang.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/instance",method = RequestMethod.GET)
	public String instance() {
		return restTemplate.getForEntity("http://SERVICE/service/instance", String.class).getBody();
	}
	
}
