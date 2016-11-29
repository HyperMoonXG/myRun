package com.geetest.sdk.java.web.demo;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
	private static final String geetest_id = "bd9c9080e88968f728f91b476bd66621";
	private static final String geetest_key = "3fd5f8640c6cf4ebc02a1a50ed37134e";

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}

}
