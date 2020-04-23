package com.aetna.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (basePackages = "com.aetna.member")
public class MemberserviceappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberserviceappApplication.class, args);
	}

}
