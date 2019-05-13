package com.wingfac.MaitreyaRim.util;

import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;


public class WXPayConfigImpl implements WXPayConfig {
	
   private static WXPayConfigImpl INSTANCE;

    public static WXPayConfigImpl getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

	@Override
	public String getAppID() {
		return Constants.WXAppid;
	}

	@Override
	public InputStream getCertStream() {
		return null;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		return 600000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		return 600000;
	}

	@Override
	public String getKey() {
		return Constants.WXApiKey;
	}

	@Override
	public String getMchID() {
		return Constants.WXMchid;
	}

}
