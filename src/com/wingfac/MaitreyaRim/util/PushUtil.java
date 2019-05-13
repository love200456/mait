package com.wingfac.MaitreyaRim.util;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import cn.jiguang.common.utils.Base64;

public class PushUtil {

	private final static String BASIC_PREFIX = "Basic";

	public static void doPush(String reciver, String msg)
			throws UnsupportedEncodingException {
		String authCode = PushUtil.getBasicAuthorization(Constants.jPushKey,
				Constants.jPushSecret);
		DefaultHttpClient client = new DefaultHttpClient();
		String url = "https://api.jpush.cn/v3/push";
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json;charset=UTF-8");
		post.setHeader("Authorization", authCode);
		JSONObject param = new JSONObject();
		JSONObject ms = new JSONObject();
		JSONObject mm = new JSONObject();
		mm.put("alert", msg);
		param.put("notification", mm);
		param.put("platform", "all");
		ms.clear();
		ms.put("alias", reciver.split(","));
		param.put("audience", ms);
		ms.clear();
		ms.put("apns_production", false);
		param.put("options", ms);
		JSONObject response = null;// 相应结果
		HttpResponse res = null;
		try {
			StringEntity s = new StringEntity(param.toString(), "UTF-8");
			post.setEntity(s);
			res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(res.getEntity());
				response = JSONObject.fromObject(result);
				System.out.println(response.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void doPushBus(String reciver, String msg)
			throws UnsupportedEncodingException {
		String authCode = PushUtil.getBasicAuthorization(Constants.jPushBusKey,
				Constants.jPushBusSecret);
		DefaultHttpClient client = new DefaultHttpClient();
		String url = "https://api.jpush.cn/v3/push";
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json;charset=UTF-8");
		post.setHeader("Authorization", authCode);
		JSONObject param = new JSONObject();
		JSONObject ms = new JSONObject();
		JSONObject mm = new JSONObject();
		mm.put("alert", msg);
		param.put("notification", mm);
		param.put("platform", "all");
		ms.clear();
		ms.put("alias", reciver.split(","));
		param.put("audience", ms);
		ms.clear();
		ms.put("apns_production", false);
		param.put("options", ms);
		JSONObject response = null;// 相应结果
		HttpResponse res = null;
		try {
			StringEntity s = new StringEntity(param.toString(), "UTF-8");
			post.setEntity(s);
			res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(res.getEntity());
				response = JSONObject.fromObject(result);
				System.out.println(response.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static String getBasicAuthorization(String username,
			String password) {
		String encodeKey = username + ":" + password;
		return BASIC_PREFIX + " "
				+ String.valueOf(Base64.encode(encodeKey.getBytes()));
	}

}
