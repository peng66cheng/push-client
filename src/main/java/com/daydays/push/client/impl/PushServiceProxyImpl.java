package com.daydays.push.client.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.daydays.push.client.PushService;
import com.daydays.push.client.domain.PushException;
import com.daydays.push.client.domain.PushTask;

@Service("pushServiceProxy")
public class PushServiceProxyImpl implements PushService {

	@Resource(name = "umengPushService")
	private PushService pushService;

	@Override
	public List<PushTask> getPushResult() {
		return pushService.getPushResult();
	}

	@Override
	public void pushMsg(PushTask pushTask, String... deviceId) throws PushException {
		pushService.pushMsg(pushTask, deviceId);
	}

}
