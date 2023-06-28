package com.microservice.api.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulAuthFilter extends ZuulFilter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		
		String url = ctx.getRequest().getRequestURL().toString();
		
		if(url.contains("/v2/api-docs")) {
			return false;
		}
		
		return true;
	}

	 private static final String RESPONSE_BODY = "{\n" + "    \"timestamp\": " + "\"" + System.currentTimeMillis()+ "\"" + ",\n" + "    \"status\": 503,\n" + "    \"error\": \"Service Unavailable\"\n" + "}";
	
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		
		HttpServletRequest request = ctx.getRequest();
		
		logger.info("Proxying Method:{} Request Url:{}", request.getMethod(), request.getRequestURL());
		
		String requestAuthorization = request.getHeader("Authorization");
		
		//authorize
		String account = authorize(requestAuthorization);
		
		if(account.equalsIgnoreCase("NO ACCOUNT")) {
			ctx.setResponseBody(RESPONSE_BODY);
            ctx.getResponse().setContentType("application/json");
            ctx.getResponse().setStatus(HttpStatus.FORBIDDEN.value());
            ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            
            logger.error("ERROR Response!");
		}else {
			ctx.addZuulRequestHeader("Account", account);
		}
		
		return null;
	}
	
	
	public String authorize(String authorization) {
		String account = "NO ACCOUNT";
		
		if(!StringUtils.isEmpty(authorization)) {
			account = "TEST-ACCOUNT";
		}else {
			logger.error("Authorization Header is empty!");
		}
		
		return account;
	}
	

	@Override
	public String filterType() {
		//optional return -> pre/post/error
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
