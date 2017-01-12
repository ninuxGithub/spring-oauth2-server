package com.hundsun.oauth.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordHandler {

	public static String md5(String password) {
		return new Md5PasswordEncoder().encodePassword(password, null);
	}
	
	public static void main(String[] args) {
		System.out.println(PasswordHandler.md5("123"));
	}

}
