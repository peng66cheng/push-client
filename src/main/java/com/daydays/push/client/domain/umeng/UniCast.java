package com.daydays.push.client.domain.umeng;

import com.alibaba.fastjson.annotation.JSONField;
import com.daydays.push.client.domain.umeng.type.CastType;

/**
 * 单播
 */
public class UniCast extends UmengCast {

	@JSONField(name = "device_tokens")
	private String deviceToken;

	public UniCast(String deviceToken, Payload payload) {
		super(CastType.UNICAST, payload);
		this.deviceToken = deviceToken;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	@Override
	public String toString() {
		return "UniCast [deviceToken=" + deviceToken + ", " + super.toString()
				+ "]";
	}
}
