package com.xu.spider4j.entity;

import com.alibaba.fastjson.JSON;

/**
 * 歌手和音乐关系表
 * relation_artlist_music
 */
public class RelationArtlistMusic {
	private int artlistId;
	private int musicId;

	public RelationArtlistMusic() {
	}

	public RelationArtlistMusic(int artlistId, int musicId) {
		this.artlistId = artlistId;
		this.musicId = musicId;
	}

	public int getArtlistId() {
		return artlistId;
	}

	public void setArtlistId(int artlistId) {
		this.artlistId = artlistId;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
