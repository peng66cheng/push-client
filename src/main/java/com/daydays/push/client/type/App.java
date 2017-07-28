package com.daydays.push.client.type;

/**
 * APP类型
 * 
 * @author dingpc
 *
 */
public enum App {
	// 2 智能教辅 3 名师辅导 4 名校好卷 5 错题笔记 6 天天扫题
	NET_TEACH_ASSIST(0, "网络教辅"), DAYDAYS_STU(1, "天天象上"), DAYDAYS_TEACH(8, "天天象上教师版");

	private String name;
	private int id;

	private App(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
