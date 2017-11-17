package com.xu.spider4j.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 *
 * 对Url的抽取逻辑和跳转逻辑
 */
public class GithubRepoPageProcessor implements PageProcessor {
	//抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setCharset("utf-8").setRetryTimes(3).setSleepTime(100);

	//process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	@Override
	public void process(Page page) {
		//定义如何抽取页面信息，并保存下来
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
		page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
		if (page.getResultItems().get("name")==null){
			//skip this page
			page.setSkip(true);
		}
		//部分三：从页面发现后续的url地址来抓取
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
//		Spider.create(new GithubRepoPageProcessor())
//				//从"https://github.com/code4craft"开始抓
//				.addUrl("https://github.com/code4craft")
//				//开启5个线程抓取
//				.thread(5)
//				//启动爬虫
//				.run();
		Spider.create(new GithubRepoPageProcessor())
				//从"https://github.com/code4craft"开始抓
				.addUrl("https://github.com/code4craft")
				.addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
				//开启5个线程抓取
				.thread(5)
				//启动爬虫
				.run();
	}
}
