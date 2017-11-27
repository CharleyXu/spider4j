package com.xu.spider4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.xu.spider4j.mapper")
@EnableAsync
public class Spider4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spider4jApplication.class, args);
	}
}
