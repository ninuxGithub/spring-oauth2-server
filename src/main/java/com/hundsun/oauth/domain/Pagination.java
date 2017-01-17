package com.hundsun.oauth.domain;

import java.util.List;

public class Pagination<E> {
	public static final int PAGENO = 1;
	public static final int PAGESIZE = 2;

	private int startIndex = 0;
	private int pageSize = PAGESIZE;
	private int total;
	private List<E> pageList;

	public Pagination(final int pageNo, final int pageSize) {
		if (pageNo > 0 && pageSize > 0) {
			this.pageSize = pageSize;
			this.startIndex = (pageNo - 1) * pageSize;
		}
	}

	public Pagination(final int pageNo, final int pageSize, final int total, final List<E> pageList) {
		this(pageNo, pageSize);
		this.total = total;
		this.pageList = pageList;
	}

	/**
	 * 页数
	 * 
	 * @return
	 */
	public int getPageCount() {
		if (total % pageSize == 0) {
			return total / pageSize;
		} else {
			return total / pageSize + 1;
		}
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotal() {
		return total;
	}

	public List<E> getPageList() {
		return pageList;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPageList(List<E> pageList) {
		this.pageList = pageList;
	}

}
