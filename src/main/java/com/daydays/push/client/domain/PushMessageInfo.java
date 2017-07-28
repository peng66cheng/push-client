package com.daydays.push.client.domain;

import java.io.Serializable;

import com.daydays.push.client.type.CustomeAction;

/**
 * 推送消息信息
 * 
 * @author dingpc
 *
 */
public class PushMessageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1728946282818833584L;
	/**
	 * 通知栏提示文字
	 */
	private String ticker;
	/**
	 * 通知标题
	 */
	private String title;
	/**
	 * 通知文字描述
	 */
	private String text;

	/**
	 * 自定义行为
	 */
	private CustomeAction custom;

	/**
	 * 点击通知跳转的url
	 */
	private String url;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CustomeAction getCustom() {
		return custom;
	}

	public void setCustom(CustomeAction custom) {
		this.custom = custom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "{PushMessageInfo:[ticker=" + this.ticker + ";title:" + this.title + ";text=" + this.text + ";custom="
				+ this.custom + ";url" + this.url + "]}" + super.toString();
	}
}
