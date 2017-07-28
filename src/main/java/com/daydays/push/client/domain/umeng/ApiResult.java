package com.daydays.push.client.domain.umeng;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 调用结果
 */
public class ApiResult implements Serializable {

	private static final long serialVersionUID = 4432688384112362203L;

	private String ret;
	private JSONObject data;

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
	
	/**
	 * 开发者自定义消息标识ID
	 * 
	 * @return
	 */
	public String getThirdpartyId() {
		return data.getString("thirdparty_id");
	}
	

	/**
	 * 消息发送后返回的ID
	 * 
	 * @return
	 */
	public String getMsgId() {
		return data.getString("msg_id");
	}

	/**
	 * 当消息类型为于broadcast、groupcast、filecast、customizedcast 且file_id不为空的情况(任务)
	 * 
	 * @return
	 */
	public String getTaskId() {
		return data.getString("task_id");
	}

	/**
	 * 文件上传后返回的ID
	 * @return
	 */
	public String getFileId() {
		return data.getString("file_id");
	}

	@Override
	public String toString() {
		return "ApiResult [ret=" + ret + ", data=" + data + "]";
	}
}
