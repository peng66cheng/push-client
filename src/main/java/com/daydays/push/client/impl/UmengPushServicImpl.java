package com.daydays.push.client.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daydays.push.client.PushService;
import com.daydays.push.client.domain.PushException;
import com.daydays.push.client.domain.PushMessageInfo;
import com.daydays.push.client.domain.PushTask;
import com.daydays.push.client.domain.umeng.AndroidPayload;
import com.daydays.push.client.domain.umeng.ApiResult;
import com.daydays.push.client.domain.umeng.IOSPayload;
import com.daydays.push.client.domain.umeng.ListCast;
import com.daydays.push.client.domain.umeng.Payload;
import com.daydays.push.client.domain.umeng.UmengCast;
import com.daydays.push.client.domain.umeng.UniCast;
import com.daydays.push.client.domain.umeng.type.Consts;
import com.daydays.push.client.type.MessageStatus;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@Service("umengPushService")
public class UmengPushServicImpl implements PushService {

	public static Logger logger = Logger.getLogger(UmengPushServicImpl.class);

	@Value("${productionMode}")
	private boolean productionMode;

	private BlockingQueue<Future<HttpResponse<String>>> pushResponseFuture = new ArrayBlockingQueue<Future<HttpResponse<String>>>(
			100 * 1000);

	@Override
	public void pushMsg(PushTask pushTask, String... deviceIds) throws PushException {
		if (deviceIds == null || deviceIds.length < 1) {
			return;
		}
		String body = getMsgBody(pushTask, deviceIds);
		Future<HttpResponse<String>> resultFuture = httpSend(Consts.UMENG_MSG_PUSH_URL, body,
				PushAccountManage.getMasterSecret(pushTask.getApp(), pushTask.getSource()));
		try {
			pushResponseFuture.put(resultFuture);
		} catch (InterruptedException e) {
			throw new PushException(e);
		}
//		getPushResult();
	}

	/**
	 * 获取 消息体
	 * @param pushTask
	 * @param deviceIds
	 * @return
	 */
	private String getMsgBody(PushTask pushTask, String... deviceIds) {
		Payload payload = getPayload(pushTask);
		UmengCast umengCast;
		if (deviceIds.length == 1) {//如果设备id只有一个，则采用单播方式，否则使用列播方式。
			umengCast = new UniCast(deviceIds[0], payload);
		} else {
			umengCast = new ListCast(payload, deviceIds);
		}
		umengCast.setProductionMode(productionMode);
		umengCast.setAppkey(PushAccountManage.getAppKey(pushTask.getApp(), pushTask.getSource()));
		umengCast.setThirdpartyId(String.valueOf(pushTask.getId()));

		return JSON.toJSONString(umengCast);
	}

	@Override
	public List<PushTask> getPushResult() {
		if (CollectionUtils.isEmpty(pushResponseFuture)) {
			return null;
		}
		Set<Future<HttpResponse<String>>> responseFutures = new HashSet<Future<HttpResponse<String>>>();
		pushResponseFuture.drainTo(responseFutures);
		if (CollectionUtils.isEmpty(responseFutures)) {
			return null;
		}

		List<PushTask> pushTaskResults = new ArrayList<PushTask>();
		for (Future<HttpResponse<String>> responseFuture : responseFutures) {
			PushTask pushTaskResult = getPushTaskResult(responseFuture);
			if (pushTaskResult != null) {
				pushTaskResults.add(pushTaskResult);
			}
		}
		return pushTaskResults;
	}

	/**
	 * 从responseFuture中获取push返回结果
	 * 
	 * @param responseFuture
	 * @return
	 */
	private PushTask getPushTaskResult(Future<HttpResponse<String>> responseFuture) {
		try {
			HttpResponse<String> response = responseFuture.get();
			JSONObject result = JSON.parseObject(response.getBody());
			ApiResult apiResult = JSON.toJavaObject(result, ApiResult.class);

			PushTask pushTaskResult = new PushTask();
			if (StringUtils.hasText(apiResult.getThirdpartyId())) {
				pushTaskResult.setId(Long.valueOf(apiResult.getThirdpartyId()));
			}
			pushTaskResult.setTaskId(apiResult.getMsgId());
			pushTaskResult.setRetMsg(apiResult.toString());
			if (response.getStatus() != 200 || !result.getString("ret").equals(Consts.SUCCESS)) {
				logger.warn("###push.error:" + response.getBody());
				pushTaskResult.setStatus(MessageStatus.FAILED);
			} else {
				pushTaskResult.setStatus(MessageStatus.SUCCEED);
			}
			return pushTaskResult;
		} catch (Exception e) {
			logger.error("getPushTaskResult.error", e);
			return null;
		}
	}
	
	/**
	 * http 异步发送
	 * @param url 请求url
	 * @param body 请求消息体
	 * @param appMasterSecret 
	 * @return 异步发送结果
	 * @throws PushException
	 */
	private Future<HttpResponse<String>> httpSend(String url, String body, String appMasterSecret)
			throws PushException {
		logger.info("发送的postBody : " + body);
		try {
			Future<HttpResponse<String>> responseFuture = Unirest.post(getSignUrl(url, body, appMasterSecret)).header("User-Agent", "Mozilla/5.0")
					.body(body).asStringAsync();
			return responseFuture;
		} catch (Exception e) {
			throw new PushException(e);
		}
	}

	private String getSignUrl(String url, String body, String appMasterSecret) throws PushException {
		try {
			String sign = DigestUtils.md5Hex(String.format("POST%s%s%s", url, body, appMasterSecret).getBytes("utf8"));
			return String.format("%s?sign=%s", url, sign);
		} catch (UnsupportedEncodingException e) {
			throw new PushException("UnsupportedEncodingException");
		}
	}

	private Payload getPayload(PushTask pushTask) {
		Payload payload;
		switch (pushTask.getSource()) {
		case Consts.ANDROID_SOURCE:
			payload = getAndroidPayload(pushTask.getMessageContent());
			break;
		default:
			payload = getIosPayload(pushTask.getMessageContent());
		}
		return payload;
	}

	private Payload getAndroidPayload(PushMessageInfo message) {
		AndroidPayload androidpayload = new AndroidPayload(message.getTicker(), message.getTitle(), message.getText());
		androidpayload.afterOpenCustom(message.getCustom().getValue());
		return androidpayload;
	}

	private Payload getIosPayload(PushMessageInfo message) {
		IOSPayload iosPayload = new IOSPayload(message.getText());
		iosPayload.keyValue("actionPushCode", message.getCustom().getValue());
		return iosPayload;
	}

}
