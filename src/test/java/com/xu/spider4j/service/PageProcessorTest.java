package com.xu.spider4j.service;

import com.xu.spider4j.pipeline.NetEaseCloudMusicPipeline;
import com.xu.spider4j.processor.NetEaseCloudMusicPageProcessor2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageProcessorTest {
	@Autowired
	private NetEaseCloudMusicPageProcessor2 netEaseCloudMusicPageProcessor;
	@Autowired
	private NetEaseCloudMusicPipeline netEaseCloudMusicPipeline;

	@Test
	@Transactional
	public void processTest(){
		netEaseCloudMusicPageProcessor.start(netEaseCloudMusicPageProcessor,netEaseCloudMusicPipeline);
	}


}
