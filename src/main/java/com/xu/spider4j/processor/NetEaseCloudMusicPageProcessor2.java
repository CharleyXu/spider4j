package com.xu.spider4j.processor;

import com.alibaba.fastjson.JSON;
import com.xu.spider4j.entity.Artist;
import com.xu.spider4j.pipeline.NetEaseCloudMusicPipeline;

import org.springframework.stereotype.Component;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 *
 */
@Component
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
		System.out.println("---processFourth--------------------------------"+"url:"+page.getUrl());
//		List<String> all = page.getHtml().xpath("//div[@id='song-list-pre-cache']//ul[@class='f-hide']/li/a").all();
//		System.out.println("all size:"+all.size()+"\n"+all);
		String s = page.getHtml().xpath("div[@id='song-list-pre-cache']/textarea/text()").toString();
//		JSONArray jsonArray = JSONArray.parseArray(s);
//		System.out.println("size"+jsonArray.size());
		page.putField("result",page.getHtml().xpath("div[@id='song-list-pre-cache']/textarea/text()").toString());
	}

	@Override
	public Site getSite() {
		return site;
	}


	public void start(NetEaseCloudMusicPageProcessor2 pageProcessor, NetEaseCloudMusicPipeline pipeline) {
		Spider.create(pageProcessor)
				//初始URL
				//初始页   http://music.163.com/discover/artist
				//歌手二级分类	http://music.163.com/discover/artist/cat?id=1001&initial=65
				//歌手	http://music.163.com/artist?id=2116	3688	11679
				.addUrl("http://music.163.com/artist?id=3688")
				//.setScheduler(new DepthLimitScheduler(3,true,0))
				.addPipeline(pipeline)
				//开启n个线程抓取
				.thread(1)
				.run();
	}

	public static void main(String[] args) {
		String jsonarray  = "[{\"name\":\"刘欢\",\"id\":3688},{\"name\":\"那英\",\"id\":9061},{\"name\":\"王力宏\",\"id\":5346,\"alia\":[\"Leehom Wang\"]},{\"name\":\"蔡依林\",\"id\":7219,\"alia\":[\"Jolin Tsai\"]},{\"name\":\"陈奕迅\",\"id\":2116,\"alia\":[\"Eason Chan\"]},{\"name\":\"孙燕姿\",\"id\":9272,\"alia\":[\"Stefanie Sun\"]},{\"name\":\"孙楠\",\"id\":4941},{\"name\":\"韩红\",\"id\":7891},{\"name\":\"孙悦\",\"id\":9294},{\"name\":\"容祖儿\",\"id\":9269,\"alia\":[\"Joey Yung\"]},{\"name\":\"林俊杰\",\"id\":3684,\"alia\":[\"JJ\"]},{\"name\":\"张靓颖\",\"id\":10561,\"alia\":[\"Jane Zhang\"]},{\"name\":\"周笔畅\",\"id\":10558},{\"name\":\"李宇春\",\"id\":8327,\"alia\":[\"Chris Lee\"]},{\"name\":\"韦唯\",\"id\":9632},{\"name\":\"黄晓明\",\"id\":3094},{\"name\":\"韩庚\",\"id\":3272,\"alia\":[\"Han Geng\"]},{\"name\":\"汪峰\",\"id\":5347},{\"name\":\"莫文蔚\",\"id\":8926,\"alia\":[\"Karen Mok\"]},{\"name\":\"谭晶\",\"id\":9491},{\"name\":\"阎维文\",\"id\":6085},{\"name\":\"戴玉强\",\"id\":2525},{\"name\":\"周华健\",\"id\":6456,\"alia\":[\"Emil Chau\"]},{\"name\":\"梁咏琪\",\"id\":8329,\"alia\":[\"gigi\"]},{\"name\":\"羽·泉\",\"id\":13418,\"alia\":[\"野孩子\"]},{\"name\":\"任贤齐\",\"id\":4895},{\"name\":\"林依轮\",\"id\":3799},{\"name\":\"张娜拉\",\"tns\":[\"장나라\"],\"id\":126174,\"alia\":[\"Jang Na Ra\"]},{\"name\":\"阿杜\",\"id\":1876,\"alia\":[\"A Do\",\"杜成义\"]},{\"name\":\"陈坤\",\"id\":2134},{\"name\":\"谢霆锋\",\"id\":5773},{\"name\":\"韩磊\",\"id\":3072},{\"name\":\"费翔\",\"id\":2743},{\"name\":\"汤灿\",\"id\":9493},{\"name\":\"林志玲\",\"id\":8635},{\"name\":\"许茹芸\",\"id\":9943,\"alia\":[\"Valen Hsu\"]},{\"name\":\"伍思凯\",\"id\":5357,\"alia\":[\"Sky Wu\"]},{\"name\":\"杨坤\",\"id\":6068},{\"name\":\"范玮琪\",\"id\":7652,\"alia\":[\"范范\"]},{\"name\":\"游鸿明\",\"id\":6070},{\"name\":\"周晓欧\",\"id\":6921},{\"name\":\"沙宝亮\",\"id\":4942,\"alia\":[\"沙宝\"]},{\"name\":\"满文军\",\"id\":4488},{\"name\":\"金海心\",\"id\":8151},{\"name\":\"何润东\",\"id\":3112,\"alia\":[\"Peter Ho\"]},{\"name\":\"F.I.R.\",\"id\":11562,\"alia\":[\"飞儿乐团\"]},{\"name\":\"庞龙\",\"id\":4725},{\"name\":\"吴克群\",\"id\":5350,\"alia\":[\"吴克羣\"]},{\"name\":\"胡彦斌Tiger Hu\",\"id\":3066},{\"name\":\"郑希怡\",\"id\":10595},{\"name\":\"刀郎\",\"id\":2517,\"alia\":[\"罗林\"]},{\"name\":\"金莎\",\"id\":8259,\"alia\":[\"蓝菲琳\"]},{\"name\":\"苏醒\",\"id\":4938},{\"name\":\"腾格尔\",\"id\":5198},{\"name\":\"成龙\",\"id\":2118}]";
		List<Artist> artistList = JSON.parseArray(jsonarray, Artist.class);
		System.out.println("artistList size:"+artistList.size()+"\n"+artistList);
//		List<Person> listPerson = JSON.parseArray(jsonString, Person.class);
	}

}
