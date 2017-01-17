package com.hundsun.oauth.domain;

import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 5477997172260166238L;
	private String name;
	private String id;

	public Tag(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Tag [name=" + name + ", id=" + id + "]";
	}

}
