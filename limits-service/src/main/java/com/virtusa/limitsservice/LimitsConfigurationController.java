package com.virtusa.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations()
	{
		return new LimitConfiguration(configuration.getMinimum(),configuration.getMaximum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public LimitConfiguration retrieveConfiguration()
	{
		throw new RuntimeException("Not available");
	}
	
	public LimitConfiguration fallbackRetrieveConfiguration()
	{
		return new LimitConfiguration(9,999);
	}

}
