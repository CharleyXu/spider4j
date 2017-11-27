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

	//page为页码
	public PageRequest(int page, int size, Sort... sorts) {
		this.size = size;
		this.sorts = sorts;
		this.start = (page - 1) * size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Sort[] getSorts() {
		return sorts;
	}

	public void setSorts(Sort[] sorts) {
		this.sorts = sorts;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	//排序类
	public static class Sort {
		private String field;
		private String type;//ASC	DESC
		public Sort() {
		}

		public Sort(String field) {
			this.field = field;
			type = "ASC";
		}

		public Sort(String field, String type) {
			this.field = field;
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}
	}
}