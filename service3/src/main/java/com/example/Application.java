package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	RestTemplate restTemplate(){ return new RestTemplate();}

	@Value("${server.port}")
	String serverPort;

	@RequestMapping("/call")
	@HystrixCommand()
	public String foo() throws InterruptedException{
		log.info("from service3, calling service3"+serverPort);
		Thread.sleep(100);
		return "foo from service3:"+serverPort;
	}

	@RequestMapping("/call-timeout")
	@HystrixCommand()
	public String callTimeout() throws InterruptedException{
		log.info("from service3, calling service3"+serverPort);
		Thread.sleep(1000);
		return "foo from service3:"+serverPort;
	}
}
