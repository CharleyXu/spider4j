package com.xu.spider4j.entity;

import com.alibaba.fastjson.JSON;

/**
 * 专辑实体类	暂时不用
 */
public class Album {
	private String id;
	private String name;
	private String picUrl;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
