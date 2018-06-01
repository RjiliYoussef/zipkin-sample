package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class Service {

    private static final Logger log = LoggerFactory.getLogger(Service.class);

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){ return new RestTemplate();}

    @HystrixCommand()
	@NewSpan(name = "service4.service.foo()")
    public String foo() throws InterruptedException{
        log.info("calling service5");
        String ret = restTemplate.getForObject("http://service5/call",String.class);
        return "foo from service5:"+ret;
    }
}
