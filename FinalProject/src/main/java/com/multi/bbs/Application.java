package com.multi.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


// Spring Security 셋팅 무효화 사유 : 안하면 인증서 대신 하는 기능을 작동 시켜야함
 @SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
