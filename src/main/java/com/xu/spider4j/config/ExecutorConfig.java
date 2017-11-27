package com.xu.spider4j.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
/**
 * 自定义异步线程池
 */
public class ExecutorConfig{

	private static final Logger log = LoggerFactory.getLogger(ExecutorConfig.class);

	/** Set the ThreadPoolExecutor's core pool size. */
	private int corePoolSize = 10;
	/** Set the ThreadPoolExecutor's maximum pool size. */
	private int maxPoolSize = 50;
	/** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
	private int queueCapacity = 10;

	@Bean(name ="myAsync")
	public Executor mySimpleAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MySimpleExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
		executor.initialize();
		return executor;
	}

	@Bean(name = "taskAsync")
	public AsyncTaskExecutor myAsyncTask() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);//当前线程数
		executor.setMaxPoolSize(maxPoolSize);// 最大线程数
		executor.setQueueCapacity(queueCapacity);//线程池所使用的缓冲队列
		executor.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
		executor.setAwaitTerminationSeconds(60 * 15);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
		executor.setThreadNamePrefix("MyAsyncTaskExecutor-");//  线程名称前缀
		// 自定义设置拒绝策略
//		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//			@Override
//			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//				// .....
//			}
//		});

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		 executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//初始化
		executor.initialize();
		return executor;
	}

}
