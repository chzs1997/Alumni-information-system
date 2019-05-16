package com.winterchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@EnableWebSocket
@SpringBootApplication
@MapperScan("com.winterchen.dao")
@EnableWebMvc
public class Springboot2MybatisDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args)
	{
		SpringApplication.run(Springboot2MybatisDemoApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Springboot2MybatisDemoApplication.class);
	}


}

