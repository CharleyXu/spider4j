package com.xu.spider4j.entity;

import com.alibaba.fastjson.JSON;

/**
 *歌手实体类
 */
public class Artist {
	private int id;
	private String name;
	private String alia;//别名

	public Artist() {
	}

	public Artist(int id, String name, String alia) {
		this.id = id;
		this.name = name;
		this.alia = alia;
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

	public String getAlia() {
		return alia;
	}

	public void setAlia(String alia) {
		this.alia = alia;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
