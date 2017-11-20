package com.xu.spider4j.processor;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class NetEaseCloudMusicPageProcessor2 implements PageProcessor {
	//正则表达式\\. \\转义java中的\  \.转义正则中的.
	//主域名
	public static final String BASE_URL = "http://music.163.com/";

	//匹配专辑URL
	public static final String ALBUM_URL = "http://music\\.163\\.com/album\\?id=\\d+";

	//歌手页
	public static final String ARTIST = "http://music\\.163\\.com/artist\\?id=\\d+";

	//歌手分类
	public static final String ARTIST_CAT_URL = "http://music\\.163\\.com/discover/artist/cat\\?id=\\d+";

	//歌手二级分类		热门	a-z		其他	整数	d+			正整数	[0-9]*[1-9][0-9]*
	public static final String ARTIST_CAT_SUB_URL = "http://music\\.163\\.com/discover/artist/cat\\?id=\\d+\\&initial=\\d+";

	//匹配歌曲URL
	public static final String MUSIC_URL = "http://music\\.163\\.com/song\\?id=\\d+";

	//初始地址,  歌手首页(推荐歌手)
	// http://music.163.com/album?id=34720827
	//
	public static final String START_URL = "http://music.163.com/discover/artist";

	@Override
	public void process(Page page) {
		if (page.getUrl().toString().equals(START_URL)) {
			processFirst(page);
		}else if (page.getUrl().regex(ARTIST_CAT_SUB_URL).match()) {
			processThird(page);
		}else if (page.getUrl().regex(ARTIST_CAT_URL).match()) {
			processSecond(page);
		}else if(page.getUrl().regex(ARTIST).match()) {
			processFourth(page);
		}else{
			System.out.println("正则匹配错误");
		}
	}

	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(5000).setCharset("UTF-8");

	private void processFirst(Page page) {
		List<String> list = page.getHtml().xpath("//div[@id=\"singer-cat-nav\"]/").links().regex(ARTIST_CAT_URL).all();
		page.addTargetRequests(list);
	}

	private void processSecond(Page page) {
//		System.out.println("url:"+page.getUrl());//http://music.163.com/discover/artist/cat?id=6001&initial=75
		List<String> list = page.getHtml().xpath("//ul[@class=\"n-ltlst f-cb\"]").links().regex(ARTIST_CAT_SUB_URL).all();
//		System.out.println("all size:"+all.size()+"\n"+all);
		page.addTargetRequests(list);
	}

	private void processThird(Page page) {
		List<String> list = page.getHtml().xpath("//ul[@id=\"m-artist-box\"]").links().regex(ARTIST).all();
		page.addTargetRequests(list);
	}

	private void processFourth(Page page){
		//http://music.163.com/artist?id=858530
		System.out.println("---processFourth--------------------------------"+"url:"+page.getUrl());
//		String url = page.getUrl().toString();
//		page.putField("title", page.getHtml().xpath("//em[@class='f-ff2']/text()"));
//		page.putField("author", page.getHtml().xpath("//p[@class='des s-fc4']/span/a/text()"));
//		page.putField("album", page.getHtml().xpath("//p[@class='des s-fc4']/a/text()"));
//		page.putField("URL", url);
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new NetEaseCloudMusicPageProcessor2())
				//初始URL
				.addUrl("http://music.163.com/discover/artist/cat?id=6001&initial=75")
				//.setScheduler(new DepthLimitScheduler(3,true,0))
				.addPipeline(new ConsolePipeline())
				//开启5个线程抓取
				.thread(1)
				.run();
	}
}
