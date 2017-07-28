package com.daydays.push.client.type;

/**
 * 消息状态
 */
public enum MessageStatus {
	
	FAILED(0, "发送失败"), SUCCEED(1, "发送成功");

	private int id;
	private String name;

	private MessageStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public MessageStatus getById(int enumId) {
		for (MessageStatus messageStatus : MessageStatus.values()) {
			if (messageStatus.getId() == enumId) {
				return messageStatus;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
