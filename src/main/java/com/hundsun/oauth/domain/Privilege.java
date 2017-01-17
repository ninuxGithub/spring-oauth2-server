package com.hundsun.oauth.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

/**
 * @function:权限的控制
 * @spring-oauth2-server :项目名称
 * @com.hundsun.oauth.domain.Privilege.java 类全路径
 * @2017年1月10日 下午2:59:36
 * @Privilege
 * 
 * 			只有两个字段进行控制才会形成权限的控制
 * 
 *          client :相当于角色的权限 Privilege:相当于角色
 *
 */
public enum Privilege {
	//USER("USER", "user-resource"), // Default privilege
	ADMIN("ADMIN", "admin-resource"), 
	UNITY("UNITY", "unity-resource"), 
	MOBILE("MOBILE", "mobile-resource");

	/** 存放到user_privilege表格 与用户的id对应起来 **/
	private String name;

	/** 存放到oauth_client_details 表格的resources_ids **/
	private String sourceId;

	private Privilege(String name, String sourceId) {
		this.name = name;
		this.sourceId = sourceId;
	}
	
	public static List<Privilege> getAllPrivileges(){
		List<Privilege> list = new ArrayList<>();
		for(Privilege p: Privilege.values()){
			list.add(p);
		}
		return list;
	}
	
	
	public static String toJson(List<Tag> tags){
		JSONArray ja = JSONArray.fromObject(tags);
		return ja.toString();
	}
	
	public static String toJson(Map<String,String> map){
		JSONArray ja = JSONArray.fromObject(map);
		return ja.toString();
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

}