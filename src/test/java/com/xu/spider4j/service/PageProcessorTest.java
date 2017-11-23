package com.xu.spider4j.service;

import com.xu.spider4j.pipeline.NetEaseCloudMusicPipeline;
import com.xu.spider4j.processor.NetEaseCloudMusicPageProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * PageProcessor测试类
 * 可直接运行processTest方法
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageProcessorTest {
	@Autowired
	private NetEaseCloudMusicPageProcessor netEaseCloudMusicPageProcessor;
	@Autowired
	private NetEaseCloudMusicPipeline netEaseCloudMusicPipeline;

	@Test
	public void processTest(){
		netEaseCloudMusicPageProcessor.start(netEaseCloudMusicPageProcessor,netEaseCloudMusicPipeline);
	}


}
