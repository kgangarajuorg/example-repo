package com.example.flights.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class EventFilter implements Filter{

	private static final Logger LOGGER = LoggerFactory.getLogger( EventFilter.class );
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
				
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		long start = System.currentTimeMillis();
		
		LOGGER.info("START_EVENT method {} url {} ", httpRequest.getMethod(), httpRequest.getServletPath());
		
		chain.doFilter(httpRequest, httpResponse);
		
		long end = System.currentTimeMillis();
		LOGGER.info("END_EVENT response_status {} time {}", httpResponse.getStatus(), (end - start));
	}

	@Override
	public void destroy() {
		
		
	}

}
