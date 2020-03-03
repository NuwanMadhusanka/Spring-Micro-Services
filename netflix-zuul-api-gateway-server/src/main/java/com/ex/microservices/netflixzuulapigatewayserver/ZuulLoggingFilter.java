package com.ex.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean shouldFilter() {
		//Can run business logic and then decide whether filter execute or not
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//Real logic executed happening in this function
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		return null;
	}

	@Override
	public String filterType() {
		//When Should the filter happening
		// "pre" --> Before the request is executed
		// "post" --> After the request is executed
		// "error" -->  Only error happening
		return "pre";
	}

	@Override
	public int filterOrder() {
		//Give priority order when multiple filters run
		return 1;
	}

}
