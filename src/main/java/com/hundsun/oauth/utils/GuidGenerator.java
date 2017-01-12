package com.hundsun.oauth.utils;

import java.util.UUID;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

/**
 * @function: 获取ClientSecret
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.utils.GuiGenerator.java 类全路径
 * @2016年12月30日 下午2:51:17
 * @GuiGenerator
 *
 */
public class GuidGenerator {
	private static RandomValueStringGenerator secrectGenerator = new RandomValueStringGenerator(32);


	public static String generateClientSecret() {
		return secrectGenerator.generate();
	}

	public static String generate() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		String code = GuidGenerator.generate();
		System.out.println(code);
		System.out.println(code.length());

		System.out.println(GuidGenerator.generateClientSecret());

	}

}
