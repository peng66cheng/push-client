package com.daydays.push.client.domain.umeng;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.daydays.push.client.domain.umeng.type.CastType;

/**
 * 友盟消息
 */
public abstract class UmengCast {

	private String appkey; // 必填 应用唯一标识
//	private String AppMasterSecret;
	
	private String timestamp; // 必填 时间戳，10位或者13位均可，时间戳有效期为10分钟
	private String type; // 必填 消息发送类型
	@JSONField(name = "production_mode")
	private String productionMode; // 可选 正式/测试模式。测试模式下，只会将消息发给测试设备。
	
	private String description; // 可选 发送消息描述，建议填写。
	@JSONField(name = "thirdparty_id")
	private String thirdpartyId; // 可选 开发者自定义消息标识ID, 开发者可以为同一批发送的多条消息
									// 提供同一个thirdparty_id, 便于友盟后台后期合并统计数据。

	private JSONObject payload; // 必填 消息内容(Android最大为1840B), 包含参数说明如下(JSON格式)

	private Policy policy; // 可选 发送策略

	public UmengCast(CastType castType, Payload payload) {
		this.timestamp = Long.toString(System.currentTimeMillis());
		this.type = castType.name().toLowerCase();
		this.productionMode = "true";
		this.payload = payload.asContent();
	}

	public JSONObject getPayload() {
		return (JSONObject) payload.clone();
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getType() {
		return type;
	}

	public String getProductionMode() {
		return productionMode;
	}

	public void setProductionMode(boolean productionMode) {
		this.productionMode = Boolean.toString(productionMode);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThirdpartyId() {
		return thirdpartyId;
	}

	public void setThirdpartyId(String thirdpartyId) {
		this.thirdpartyId = thirdpartyId;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Policy getPolicy() {
		return policy;
	}

	@Override
	public String toString() {
		return "appkey=" + appkey + ", timestamp=" + timestamp + ", type="
				+ type + ", productionMode=" + productionMode
				+ ", description=" + description + ", thirdpartyId="
				+ thirdpartyId + ", payload=" + payload + ", policy=" + policy;
	}
}
