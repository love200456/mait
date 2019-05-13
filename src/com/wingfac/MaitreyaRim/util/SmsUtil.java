package com.wingfac.MaitreyaRim.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.dysmsapi.transform.v20170525.SendSmsResponseUnmarshaller;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SmsUtil {

	static final String product = "Dysmsapi";
	static final String domain = "dysmsapi.aliyuncs.com";

	static final String accessKeyId = "LTAI6y8jIMH5yYMd";
	static final String accessKeySecret = "7ob1WFQMEY1Zjj8BA8C1e9D2Rp42RR";

	public static SendSmsResponse sendSms(String phone, String code) throws ClientException {
		System.setProperty("sun.net.client.defaultConnectTimeout", "50000");
		System.setProperty("sun.net.client.defaultReadTimeout", "50000");
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		SendSmsRequest request = new SendSmsRequest();
		request.setPhoneNumbers(phone);
		request.setSignName("承明网络");
		request.setTemplateCode("SMS_100215046");
		request.setTemplateParam("{'code':'"+code+"'}");
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}
	
}
