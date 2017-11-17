package com.xu.spider4j.service;

import com.xu.spider4j.service.Sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMqHelloTest {
	@Autowired
	private Sender sender;

	@Test
	public void hello(){
		sender.send();
	}
}
