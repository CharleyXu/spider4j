package com.xu.spider4j.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * 深度爬取的限制处理
 * request的setExtras方法
 */
public class DepthLimitScheduler extends QueueScheduler {
	private int levelLimit;
	private boolean start;
	private int START_DEPT;

	public DepthLimitScheduler() {
	}

	public DepthLimitScheduler(int levelLimit, boolean start, int START_DEPT) {
		this.levelLimit = levelLimit;
		this.start = start;
		this.START_DEPT = START_DEPT;
		System.out.println("初始化DepthLimitScheduler");
	}

	private BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();

	@Override
	public void pushWhenNoDuplicate(Request request, Task task) {
		if (start == true) {
			Map<String, Object> extras = new HashMap<String, Object>();
			extras.put("_level", this.START_DEPT + 1);
			request.setExtras(extras);
			queue.add(request);
		} else {
			if (((Integer) request.getExtra("_level")) <= levelLimit) {
				//获取上层页面的深度再加一就是这个URL的深度
				Map<String, Object> extras = new HashMap<String, Object>();
				extras.put("_level", (Integer) request.getExtra("_level") + 1);
				request.setExtras(extras);
				queue.add(request);
			} else {
				System.out.println("已到达设定深度");
			}
		}
		start = false;
	}
}
