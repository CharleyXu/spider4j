package com.xu.spider4j.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * NetEaseMusic 工具类
 */
public class NetEaseMusicApi {
	private final static String USER_PLAYLIST = "http://music.163.com/weapi/user/playlist?csrf_token=";
	private static final String PLAYLIST ="http://music.163.com/weapi/v3/playlist/detail?csrf_token=";
	private final static String COMMENT_URL = "http://music.163.com/weapi/v1/resource/comments/R_SO_4_";
	/**
	 * 获取用户歌单
	 *	默认20条歌单
	 * @param uid
	 * @return
	 */
	public static String getPlaylistOfUser(String uid) {
		int offerset = 0;
		int limit = 20;
		String first_param = "{offset:\"offset_param\", uid:\"uid_param\", limit:\"limit_param\", csrf_token:\"\"}";
		first_param = first_param.replace("uid_param", uid)
				.replace("offset_param", offerset + "")
				.replace("limit_param", limit+"");
		String result = crawler(USER_PLAYLIST, first_param);
		return result;
	}

	/**
	 * 获取歌单详情
	 *
	 * @param playlist_id
	 * @return
	 */
	public static String getDetailOfPlaylist(String playlist_id) {
		int offerset = 0;
		String total = "True";
		int limit = 1000;
		int n = 1000;
		String first_param = "{id:\"id_param\",offset:\"offset_param\", total:\"total_param\", limit:\"limit_param\",n:\"n_param\", csrf_token:\"\"}";
		first_param = first_param.replace("id_param", playlist_id)
				.replace("offset_param", offerset + "")
				.replace("total_param", total)
				.replace("limit_param", limit + "")
				.replace("n_param", n+"");

		String result = crawler(PLAYLIST, first_param);
		return result;
	}


	public static String getCommentUrl(String musicId){
		int offset = 0;
		String first_param = "{rid:\"\", offset:\"offset_param\", total:\"true\", limit:\"20\", csrf_token:\"\"}";
		first_param = first_param.replace("offset_param", offset + "");
		String url = COMMENT_URL+musicId;

		String result = crawler(url, first_param);
		return result;
	}

	public static String crawler(String url , String first_param){
		Connection.Response response = null;
		String result = null;
		try {
			response = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:57.0) Gecko/20100101 Firefox/57.0")
					.header("Accept", "*/*")
					.header("Cache-Control", "no-cache")
					.header("Connection", "keep-alive")
					.header("Host", "music.163.com")
					.header("Accept-Language", "zh-CN,en-US;q=0.7,en;q=0.3")
					.header("DNT", "1")
					.header("Pragma", "no-cache")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.data(JSUtil.getDatas(first_param))
					.method(Connection.Method.POST)
					.ignoreContentType(true)
					.timeout(10000)
					.execute();
			result = response.body();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String playlistOfUser = getPlaylistOfUser("72648534");
		System.out.println(playlistOfUser);
		String detailOfPlaylist = getDetailOfPlaylist("78199745");
		System.out.println(detailOfPlaylist);
		String commentInfo = getCommentUrl("108251");
		System.out.println(commentInfo);
	}
}
