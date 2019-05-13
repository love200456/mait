package com.wingfac.MaitreyaRim.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Constants {

//	public static String aLiAppKey = "23531363";
//	public static String aLiSecret = "0b904aa8f96ae609d257521161e99920";

	public static final String speader = System.getProperty("file.separator");

	public static final Integer sun = 10;

	public static final Integer sun12 = 12;

	public static final String orUrl = "http://qr.topscan.com/api.php?w=200&m=0&";

	public static final String jPushKey = "ab651128f018bc1bae532016";
	public static final String jPushSecret = "ef2ca6910dacf89e6da0dd48";

	public static final String jPushBusKey = "ea304806d8dab0f976d412d1";
	public static final String jPushBusSecret = "fe647328add49b2e1515e6fa";

	public static final String WXApiKey = "00ac6dfff0914384bdb563f2423bbf46";
	public static final String WXAppid = "wxcdd9d9b9c0bbf16d";
	public static final String WXMchid = "1484832852";
	public static final String WXNotifyUrl = "http://47.92.50.159/MaitreyaRim/OrderInformationMoblie/WXPaySuccess.action";

	public static String getRandom4() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 4; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	public static String getSystemTime() {
		String systemDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(new Date());
		return systemDate;
	}

}
