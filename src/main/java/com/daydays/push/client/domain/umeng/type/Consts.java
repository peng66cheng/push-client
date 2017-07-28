package com.daydays.push.client.domain.umeng.type;

public final class Consts {
	public static final String SUCCESS = "SUCCESS";

	public static final int ANDROID_SOURCE = 20;
	public static final int IOS_SOURCE = 30;
	
	private final static String UMENG_API_BASE = "http://msg.umeng.com";
	public final static String UMENG_MSG_PUSH_URL = UMENG_API_BASE + "/api/send";
	public final static String UMENG_MSG_STATUS_URL = UMENG_API_BASE + "/api/status";
	public final static String UMENG_MSG_CANCEL_URL = UMENG_API_BASE + "/api/cancel";
	public final static String UMENG_MSG_UPLOAD_URL = UMENG_API_BASE + "/upload";
	
}
