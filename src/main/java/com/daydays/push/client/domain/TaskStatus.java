package com.daydays.push.client.domain;

import java.io.Serializable;

import com.daydays.push.client.type.MessageStatus;

/**
 * 任务状态
 * 
 * 提供给“任务状态查询”接口使用
 */
@Deprecated
public class TaskStatus implements Serializable {

	private static final long serialVersionUID = -1337189677186595410L;

	private String taskId;
	/**
	 * 消息状态: 0-排队中, 1-发送中，2-发送完成，3-发送失败，4-消息被撤销， // 5-消息过期,
	 * 6-筛选结果为空，7-定时任务尚未开始处理
	 */
	private int status;
	private int totalCount; // 消息总数
	private int acceptCount; // 消息受理数
	private int sentCount; // 消息实际发送数
	private int openCount;// 打开数
	private int dismissCount;// 忽略数

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public MessageStatus getStatus() {
		return MessageStatus.values()[status];
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getAcceptCount() {
		return acceptCount;
	}

	public void setAcceptCount(int acceptCount) {
		this.acceptCount = acceptCount;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

	public int getOpenCount() {
		return openCount;
	}

	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}

	public int getDismissCount() {
		return dismissCount;
	}

	public void setDismissCount(int dismissCount) {
		this.dismissCount = dismissCount;
	}

	@Override
	public String toString() {
		return "TaskStatus [taskId=" + taskId + ", status=" + status + ", totalCount=" + totalCount + ", acceptCount="
				+ acceptCount + ", sentCount=" + sentCount + ", openCount=" + openCount + ", dismissCount="
				+ dismissCount + "]";
	}
	
}
