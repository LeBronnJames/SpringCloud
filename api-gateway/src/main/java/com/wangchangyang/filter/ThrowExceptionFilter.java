package com.wangchangyang.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ThrowExceptionFilter extends ZuulFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object run() throws ZuulException {

		logger.info("This is a pre filter,it will throw a RutimeException");
		doSomething();
		return null;
	}

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public int filterOrder() {

		return 0;
	}

	@Override
	public String filterType() {

		return "pre";
	}
	
	private void doSomething() {
		throw new RuntimeException("this is a RuntimeException");
	}

}
