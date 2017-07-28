package com.daydays.push.client.domain.umeng;

import com.alibaba.fastjson.annotation.JSONField;
import com.daydays.push.client.domain.umeng.type.CastType;

/**
 * 列播
 * 
 * @author root
 *
 */
public class ListCast extends UmengCast {

	@JSONField(name = "device_tokens")
	private String deviceTokens;

	public ListCast(Payload payload, String... deviceTokens) {
		super(CastType.LISTCAST, payload);
		this.deviceTokens = concatComma(deviceTokens);
	}

	public String getDeviceTokens() {
		return deviceTokens;
	}

	@Override
	public String toString() {
		return "ListCast [deviceTokens=" + deviceTokens + ", " + super.toString() + "]";
	}

	public static String concatComma(String... params) {
		StringBuilder sb = new StringBuilder();
		sb.append(params[0]);
		for (int i = 1; i < params.length; i++) {
			sb.append(",").append(params[i]);
		}
		return sb.toString();
	}
}
