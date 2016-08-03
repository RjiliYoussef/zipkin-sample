package com.samsung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleAppApplication.class, args);
	}

	@RequestMapping("/")
	public String call(){
		return "hello world";
	}


}

@Configuration
class MyAuth extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()
			throws Exception {
		return super.authenticationManagerBean();
	}

}