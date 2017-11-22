package com.xu.spider4j.entity;

import com.alibaba.fastjson.JSON;

/**
 * 歌手和音乐关系表
 * relation_artlist_music
 */
public class RelationArtlistMusic {
	private int artistId;
	private int musicId;

	public RelationArtlistMusic() {
	}

	public RelationArtlistMusic(int artistId, int musicId) {
		this.artistId = artistId;
		this.musicId = musicId;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
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
