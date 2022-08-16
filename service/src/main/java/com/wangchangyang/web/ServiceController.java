package com.wangchangyang.web;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${server.port}")
	private int port; // 服务端口
	
	@Autowired
	private Registration registration; // 服务注册

	@Autowired
	private DiscoveryClient client; // 服务发现客户端

	@RequestMapping(value = "/instance", method = RequestMethod.GET)
	public String instance() throws Exception {
		ServiceInstance instance = serviceInstance();
		// 测试超时
		int sleepTime = new Random().nextInt(200);
		logger.info("instance,sleepTime:" + sleepTime);
		Thread.sleep(sleepTime);
		
		return "/instance,host:" + instance.getHost() + ",service_id:" + instance.getServiceId();
	}

	public ServiceInstance serviceInstance() {
		List<ServiceInstance> list = client.getInstances(registration.getServiceId());
		if (list != null && list.size() > 0) {
			for (ServiceInstance itm : list) {
				if (itm.getPort() == port)
					return itm;
			}
		}
		return null;
	}

}
