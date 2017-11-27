package com.xu.spider4j.entity;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 分页查询的结果类
 *
 */
public class Page<T> {
	private long totalRows; //总记录数
	private int totalPages; //总页数
	private List<T> rows; //查询到的记录

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
