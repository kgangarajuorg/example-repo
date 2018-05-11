package com.example.zuulgate.filters.pre;

import com.netflix.zuul.ZuulFilter;

public class FilterOne extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		System.out.println("from filterOne.........");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
