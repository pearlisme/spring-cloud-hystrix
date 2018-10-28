package com.pearl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/rest")
public class ResourceController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${goodbye.service.url}")
	private String url;
	
	@HystrixCommand(groupKey="fallback",commandKey="fallback",fallbackMethod="hystrixFallBack")
	@GetMapping("/hystrixhello")
	public String hystrixHello() {
		
		return restTemplate.getForObject(url, String.class);
//		return "hystrixHello";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	public String hystrixFallBack() {
		
		return "fallback";
		
	}

}
