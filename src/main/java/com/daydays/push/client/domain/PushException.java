package com.daydays.push.client.domain;

/**
 * 推送异常
 * 
 * @author dingpc
 *
 */
public class PushException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1980889547901345423L;

	public PushException(Exception e) {
		super(e);
	}
	
	public PushException(String msg) {
		super(msg);
	}
}
