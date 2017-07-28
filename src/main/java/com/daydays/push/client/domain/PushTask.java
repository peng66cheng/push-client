package com.daydays.push.client.domain;

import com.daydays.push.client.type.App;
import com.daydays.push.client.type.MessageStatus;

/**
 * 推送任务
 * 
 * @author dingpc
 *
 */
public class PushTask {

	/**
	 * 任务id
	 */
	private Long id;

	/**
	 * 推送任务的发起者
	 */
	private Long ownerId;

	/**
	 * APP类型
	 */
	private App app;

	/**
	 * 20 android;30 ios
	 */
	private Integer source;

	/**
	 * 消息内容
	 */
	private PushMessageInfo messageContent;

	/**
	 * 任务id
	 */
	private String taskId;

	/**
	 * 任务执行状态
	 */
	private MessageStatus status;

	/**
	 * 返回信息
	 */
	private String retMsg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public PushMessageInfo getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(PushMessageInfo messageContent) {
		this.messageContent = messageContent;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PushTask)) {
			return false;
		}
		return ((PushTask) obj).getId() == this.id;
	}

}
