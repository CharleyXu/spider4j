package com.xu.spider4j.task;

import com.xu.spider4j.config.AsyncDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskTest {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AsyncDemo asyncDemo;

	@Test
	public void asyncDemoTest() throws ExecutionException, InterruptedException {
		asyncDemo.asyncInvokeSimplest();
		asyncDemo.asyncInvokeWithParameter("test");
		Future<String> future = asyncDemo.asyncInvokeReturnFuture(100);
		System.out.println(future.get());
	}
}
