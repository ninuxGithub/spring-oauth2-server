package com.hundsun.oauth.test;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.oauth.domain.Privilege;

public class TestEnum {
	public static void main(String[] args) {
		Privilege[] ps = Privilege.values();
		
		Map<String,String> names = new HashMap<>();
		Map<String,String> ids = new HashMap<>();
		for(Privilege p : ps){
			String name = p.getName();
			String id = p.getSourceId();
			System.out.println(name +"-------"+ id);
			names.put(name, name);
			ids.put(id, id);
		}
		
		System.out.println(Privilege.toJson(names));
		System.out.println(Privilege.toJson(ids));
		
		
		//System.out.println(Privilege.toJson(Privilege.getAllPrivileges()));
		
		
		
	}
	
	
	
	

}
