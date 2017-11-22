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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Artist artist = (Artist) o;

		if (id != artist.id) return false;
		if (name != null ? !name.equals(artist.name) : artist.name != null) return false;
		return alia != null ? alia.equals(artist.alia) : artist.alia == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (alia != null ? alia.hashCode() : 0);
		return result;
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
