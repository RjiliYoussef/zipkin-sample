package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Configuration
public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}



}



@Configuration
@RefreshScope
@RestController
 class Test{

	@Value("${eureka.instance.hostname}")
	String aaa;

	@RequestMapping("/")
	public String aaa(){
		return aaa;
	}

	@RequestMapping("/static")
	public String bb(){
              return ST.aaa;
        }
}

class ST{
	@Value("${eureka.instance.hostname}")
           static String aaa= "a";

}
