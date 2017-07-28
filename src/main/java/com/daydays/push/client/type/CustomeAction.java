package com.daydays.push.client.type;

/**
 * app自定义行为
 * 
 * @author dingpc
 *
 */
public enum CustomeAction {
	
	/**
	 * 首页
	 */
	HOME_PAGE(0, "000001"),
	
	/**
	 * 订阅
	 */
	ORDER_MSG(1, "000002"), 
	
	/**
	 * 任务
	 */
	TASK(2, "000005"),
	
	
	;

	private int id;
	private String value;

	private CustomeAction(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public int getId() {
		return id;
	}

}
