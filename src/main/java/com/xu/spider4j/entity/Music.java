package com.xu.spider4j.entity;

import com.alibaba.fastjson.JSON;

/**
 * 音乐实体类
 */
public class Music {
	private int	id;//歌曲Id
	private String	name;//名称
	private String	artistsId;//歌手Id( 有 邵夷贝 / GALA 这种情况)
	private int score;//热度
	private String	album;//专辑名称
	private String commentThreadId;//评论ThreadId

	public Music() {
	}

	public Music(int id, String name, String artistsId, int score, String album, String commentThreadId) {
		this.id = id;
		this.name = name;
		this.artistsId = artistsId;
		this.score = score;
		this.album = album;
		this.commentThreadId = commentThreadId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistsId() {
		return artistsId;
	}

	public void setArtistsId(String artistsId) {
		this.artistsId = artistsId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getCommentThreadId() {
		return commentThreadId;
	}

	public void setCommentThreadId(String commentThreadId) {
		this.commentThreadId = commentThreadId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
