package com.xu.spider4j.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *创建消息生产者Sender。
 */
//@Component
public class Sender {
	//通过注入AmqpTemplate接口的实例来实现消息的发送，
	@Autowired
	private AmqpTemplate rabbitTemplate;

	//在该生产者，我们会产生一个字符串，并发送到名为hello的队列中。
	public void send(){
		String context = "hello " + new Date();
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("hello", context);
	}
}
