package com.xu.spider4j.service;

/**
 * 创建消息消费者Receiver
 * 通过@RabbitListener注解定义该类对hello队列的监听
 * 实现了对hello队列的消费，消费操作为输出消息的字符串内容。
 */
//@Component
//@RabbitListener(queues = "hello")
public class Receiver {
//	@Autowired
//	private AmqpTemplate amqpTemplate;
//	//@RabbitHandler注解来指定对消息的处理方法
//	@RabbitHandler
//	public void  process(String hello){
//		System.out.println("Receiver : " + hello);
//	}
}
