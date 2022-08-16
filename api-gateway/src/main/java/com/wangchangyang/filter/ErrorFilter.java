package com.wangchangyang.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ErrorFilter extends ZuulFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		logger.error("this is a ErrorFilter:{}",throwable.getCause().getMessage());
		throw new ZuulException(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),throwable.getCause().getMessage());
	}

	@Override
	public String filterType() {
		
		return "error";
	}

	@Override
	public int filterOrder() {
		
		return 10;
	}
	
	

}
