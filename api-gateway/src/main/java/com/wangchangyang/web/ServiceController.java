package com.wangchangyang.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@RequestMapping("/instance")
	public String instance() {
		return "api-gateway,ServiceController,instance";
	}
	
}
