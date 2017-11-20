package com.xu.spider4j.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/")
	public String index() {
		return "Hello Controller !";
	}

	@Value("${myconfig.testId}")
	private String id;

	@Value("${myconfig.testName}")
	private String name;

	@RequestMapping("/test")
	public String demo01() {
		return id + "," + name;
	}

}
