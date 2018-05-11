package com.example.zuulgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGateApplication.class, args);
	}

	/*@Bean
	public FilterOne filterOne() {
		return new FilterOne();
	}

	@Bean
	public FilterTwo filterTwo() {
		return new FilterTwo();
	}

	@Bean
	public FilterThree filterThree() {
		return new FilterThree();
	}

	@Bean
	public FilterNone filterNone() {
		return new FilterNone();
	}

	
	@Bean
	public IClientConfig ribbonClientConfig() {
		return new DefaultClientConfigImpl();
	}

	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new PingUrl();
	}

	@Bean
	public IRule ribbonRule(IClientConfig config) {
		return new AvailabilityFilteringRule();
	}*/
}
