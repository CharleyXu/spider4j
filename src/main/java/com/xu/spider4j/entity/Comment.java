package com.xu.spider4j.entity;

import com.alibaba.fastjson.JSON;

/**
 * 评论实体类
 */
public class Comment {
	private String id;//评论Id
	private String	songId;//歌曲Id
	private String	nickname;//用户昵称
	private Integer	linkedCount;//点赞数
	private String	content;//内容
	private String time;//时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getLinkedCount() {
		return linkedCount;
	}

	public void setLinkedCount(Integer linkedCount) {
		this.linkedCount = linkedCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
