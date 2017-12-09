package com.xu.spider4j.entity;

/**
 * 分页查询类
 * 分页查询的页码和页面大小，还有排序规则
 */
public class PageRequest {

	private int size; //查询记录的数量
	private Sort[] sorts; //排序
	private int start; //开始位置

	public PageRequest() {
	}

	public PageRequest(int size, Sort[] sorts, int start) {
		this.size = size;
		this.sorts = sorts.clone();
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Sort[] getSorts() {
		return sorts.clone();
	}

	public void setSorts(Sort[] sorts) {
		this.sorts = sorts.clone();
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}