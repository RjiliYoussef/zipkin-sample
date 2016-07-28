package com.example;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FallbackTest {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){ return new RestTemplate();}

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "service2_Fail_Method")
    public String service2_OK_Method() throws InterruptedException {
        return restTemplate.getForObject("http://service2/call",String.class);
    }

    public String service2_Fail_Method() throws InterruptedException{
        return "service2_Fail_Method ";
    }
}