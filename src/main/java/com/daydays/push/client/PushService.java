package com.daydays.push.client;

import java.util.List;

import com.daydays.push.client.domain.PushException;
import com.daydays.push.client.domain.PushTask;
/**
 * 消息推送服务
 * @author dingpc
 *
 */
public interface PushService {
	
	/**
	 * 推送
	 * @param pushTask 推送任务
	 * @param deviceId 推送的设备id
	 * @throws PushException 推送失败，返回pushException
	 */
	void pushMsg(PushTask pushTask,String... deviceId) throws PushException;
	
	/**
	 * 获取push结果
	 * 		因为推送为异步推送，所以提供 推送结果查询接口
	 * @return
	 */
	List<PushTask> getPushResult();

}
