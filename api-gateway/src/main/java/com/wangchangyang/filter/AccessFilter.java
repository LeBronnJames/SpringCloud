package com.wangchangyang.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AccessFilter extends ZuulFilter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		logger.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
		
		Object accessToken = request.getParameter("accessToken");
		if(accessToken == null) {
			logger.info("access token is empty");
			// 通知zuul过滤该请求，不对其进行路由
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			// 对返回的body内容进行自定义
			ctx.setResponseBody("======================触发拦截======================");
			return null;
		}
		
		logger.info("access token ok");
		return null;
		
	}
	
	/**
	 * 利用该函数来指定过滤器的有效范围
	 */
	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}
	
	/**
	 * 过滤器类型，它决定过滤器在请求的哪个生命周期中执行。
	 * pre：可以再请求被路由之前调用
	 * routing：在路由请求时被调用
	 * post：在routing和error过滤器之后被调用
	 * error：处理请求时发生错误时被调用
	 */
	
	@Override
	public String filterType() {
		
		return "pre";
	}
	

}
