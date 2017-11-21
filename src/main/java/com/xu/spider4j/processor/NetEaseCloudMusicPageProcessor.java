package com.xu.spider4j.processor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取网易云音乐专辑歌曲信息
 */
public class NetEaseCloudMusicPageProcessor implements PageProcessor {
	//正则表达式\\. \\转义java中的\  \.转义正则中的.
	//主域名
	public static final String BASE_URL = "http://music.163.com/";

	//匹配专辑URL
	public static final String ALBUM_URL = "http://music\\.163\\.com/album\\?id=\\d+";

	//匹配歌曲URL
	public static final String MUSIC_URL = "http://music\\.163\\.com/song\\?id=\\d+";

	//初始地址, JAY_CHOU 周杰伦的床边故事专辑, 可以改为其他歌手专辑地址
	public static final String START_URL = "http://music.163.com/album?id=34720827";

	//加密使用到的文本
//	public static final String TEXT = "{\"username\": \"\", \"rememberLogin\": \"true\", \"password\": \"\"}";

	//爬取结果保存文件路径	/home/user/workspace/WebMagicCrawler/result/
	public static final String RESULT_PATH = "D:\\Document\\Temp\\";

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(5000);

//	private Site site = Site.me()
//			.setDomain("http://music.163.com")
//			.setSleepTime(1000)
//			.setRetryTimes(3)
//			.setTimeOut(30000)
//			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		//根据URL判断页面类型
		if (page.getUrl().regex(ALBUM_URL).match()) {
			//爬取歌曲URl加入队列
			page.addTargetRequests(page.getHtml().xpath("//div[@id=\"song-list-pre-cache\"]").links().regex(MUSIC_URL).all());
			//爬取专辑URL加入队列
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"cver u-cover u-cover-3\"]").links().regex(ALBUM_URL).all());
		} else {
			String url = page.getUrl().toString();
			page.putField("title", page.getHtml().xpath("//em[@class='f-ff2']/text()"));
			page.putField("author", page.getHtml().xpath("//p[@class='des s-fc4']/span/a/text()"));
			page.putField("album", page.getHtml().xpath("//p[@class='des s-fc4']/a/text()"));
			page.putField("URL", url);
			//单独对AJAX请求获取评论数, 使用JSON解析返回结果
//			page.putField("commentCount", JSONPath.eval(JSON.parse(NetEaseMusicUtil.crawlAjaxUrl(url.substring(url.indexOf("id=") + 3),0)), "$.total"));
		}
	}

	public static void main(String[] args) {
//		Spider.create(new NetEaseCloudMusicPageProcessor())
//				//初始URL
//				.addUrl(START_URL)
////				.addPipeline(new ConsolePipeline())
//				//结果输出到文件
////				.addPipeline(new JsonFilePipeline(RESULT_PATH))
//				.addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
//				//开启5个线程抓取
//				.thread(5)
//				.run();

		String artistsId = "3688,9061,5346,7219,2116,9272,4941,7891,9294,9269,3684,10561,10558,8327,9632,3094,3272,5347,8926,9491,6085,2525,6456,8329,13418,4895,3799,126174,1876,2134,5773,3072,2743,9493,8635,9943,5357,6068,7652,6070,6921,4942,4488,8151,3112,11562,4725,5350,3066,10595,2517,8259,4938,5198,2118";
		System.out.println(artistsId.length());
	}

}

