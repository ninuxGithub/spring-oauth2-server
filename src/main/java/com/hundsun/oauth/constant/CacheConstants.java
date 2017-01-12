package com.hundsun.oauth.constant;

/**
 * @function:缓存常量
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.constant.CacheConstants.java 类全路径
 * @2016年12月30日 下午4:41:07
 * @CacheConstants
 *
 */
public class CacheConstants {
	/**
	 * client Details Cache, key is clientId
	 */
	public static final String CLIENT_DETAILS_CACHE = "clientDetailsCache";

	/**
	 * access Token Cache, key is token
	 */
	public static final String ACCESS_TOKEN_CACHE = "accessTokenCache";

	/**
	 * refresh Token Cache, key is token
	 */
	public static final String REFRESH_TOKEN_CACHE = "refreshTokenCache";

	/**
	 * authorization Code Cache, key is code
	 */
	public static final String AUTHORIZATION_CODE_CACHE = "authorizationCodeCache";

	/**
	 * user Cache, key is username
	 */
	public static final String USER_CACHE = "userCache";
}
