package com.xu.spider4j.config;

import com.google.common.base.Stopwatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncDemo {
	private static final Logger log = LoggerFactory.getLogger(AsyncDemo.class);

	/**
	 * 最简单的异步调用，返回值为void
	 *
	 */
	@Async("myAsync")
	public void asyncInvokeSimplest() {
		log.info("asyncSimplest");
	}

	/**
	 * 带参数的异步调用 异步方法可以传入参数
	 * 对于返回值是void，异常会被AsyncUncaughtExceptionHandler处理掉
	 * @param s
	 */
	@Async("taskAsync")
	public void asyncInvokeWithParameter(String s) {
		log.info("asyncInvokeWithParameter, parementer={}", s);
//		throw new IllegalArgumentException(s);
	}

	/**
	 * 异常调用返回Future
	 * 对于返回值是Future，不会被AsyncUncaughtExceptionHandler处理，需要我们在方法中捕获异常并处理
	 * 或者在调用方在调用Futrue.get时捕获异常进行处理
	 * @param i
	 * @return
	 */
	@Async("taskAsync")
	public Future<String> asyncInvokeReturnFuture(int i) {
		log.info("asyncInvokeReturnFuture, parementer={}", i);
		Future<String> future;
		Stopwatch started = Stopwatch.createStarted();
		try {
			Thread.sleep(2000 * 1);
			long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
			future = new AsyncResult<String>("success:" + i+" ,use "+elapsed+"ms");
		} catch (InterruptedException e) {
			future = new AsyncResult<String>("error-InterruptedException");
		} catch(IllegalArgumentException e){
			future = new AsyncResult<String>("error-IllegalArgumentException");
		}
		return future;
	}

}
