package com.daydays.push.client.impl;

import com.daydays.push.client.domain.umeng.type.Consts;
import com.daydays.push.client.type.App;

public class PushAccountManage {

	/**
	 * 天天象上教师版APP
	 */
	public final static String DAYDAYS_TEACH_ANDROID_APP_KEY = "569f5827e0f55aa40f0008d8";
	public final static String DAYDAYS_TEACH_ANDROID_MASTER_SECRET = "6edgy0iryra8sko4oeuh9zdl2wqu14zg";
	public final static String DAYDAYS_TEACH_IOS_APP_KEY = "569f585fe0f55aa3e1000b44";
	public final static String DAYDAYS_TEACH_IOS_MASTER_SECRET = "qvoqj89k7kznrxc9d1ifwclab1ikswni";
	
	/**
	 * 天天象上APP
	 */
	public final static String DAYDAYS_STU_ANDROID_APP_KEY = "5546df3667e58e1f8d0010a6";
	public final static String DAYDAYS_STU_ANDROID_MASTER_SECRET = "9uf5llnobs13vghpbuacnaspvppyzuqu";
	public final static String DAYDAYS_STU_IOS_APP_KEY = "55dbd1ab67e58e85c700206b";
	public final static String DAYDAYS_STU_IOS_MASTER_SECRET = "ofjt626l5sc98wlkruc3cbudzkw6oc7z";
	
	public static String getAppKey(App app, int source) {
		if (App.DAYDAYS_TEACH == app && source == Consts.ANDROID_SOURCE) {
			return DAYDAYS_TEACH_ANDROID_APP_KEY;
		}
		if (App.DAYDAYS_TEACH == app && source == Consts.IOS_SOURCE) {
			return DAYDAYS_TEACH_IOS_APP_KEY;
		}
		if (App.DAYDAYS_STU == app && source == Consts.ANDROID_SOURCE) {
			return DAYDAYS_STU_ANDROID_APP_KEY;
		}
		if (App.DAYDAYS_STU == app && source == Consts.IOS_SOURCE) {
			return DAYDAYS_STU_IOS_APP_KEY;
		}
		return null;
	}

	public static String getMasterSecret(App app, int source) {
		if (App.DAYDAYS_TEACH == app && source == Consts.ANDROID_SOURCE) {
			return DAYDAYS_TEACH_ANDROID_MASTER_SECRET;
		}
		if (App.DAYDAYS_TEACH == app && source == Consts.IOS_SOURCE) {
			return DAYDAYS_TEACH_IOS_MASTER_SECRET;
		}
		if (App.DAYDAYS_STU == app && source == Consts.ANDROID_SOURCE) {
			return DAYDAYS_STU_ANDROID_MASTER_SECRET;
		}
		if (App.DAYDAYS_STU == app && source == Consts.IOS_SOURCE) {
			return DAYDAYS_STU_IOS_MASTER_SECRET;
		}
		return null;
	}
}
