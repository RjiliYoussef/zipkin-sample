package com.example;

import com.keyholesoftware.troublemaker.client.EnableTroubleMaker;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@EnableTroubleMaker
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	RestTemplate restTemplate(){ return new RestTemplate();}

	@Value("${server.port}")
	String serverPort;

	@Value("${greeting.displayFortune:myfortune}")
	String greeting;

	@RequestMapping("/call")
	@HystrixCommand()
	public String foo() throws InterruptedException{
		log.info("from service5 "+serverPort);
		return "foo from service5:"+serverPort +greeting;
	}

	@RequestMapping("/call-timeout")
	@HystrixCommand()
	public String callTimeout() throws InterruptedException{
		log.info("from service5"+serverPort);
		Thread.sleep(1000);
		return "foo from service5:"+serverPort;
	}


}
