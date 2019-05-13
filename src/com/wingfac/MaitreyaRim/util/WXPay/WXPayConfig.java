package com.wingfac.MaitreyaRim.util.WXPay;


import java.io.InputStream;

public abstract class WXPayConfig {



    
    abstract String getAppID();


    
    abstract String getMchID();


    
    abstract String getKey();


    
    abstract InputStream getCertStream();

   
    public int getHttpConnectTimeoutMs() {
        return 6*1000;
    }

    
    public int getHttpReadTimeoutMs() {
        return 8*1000;
    }

    
    abstract IWXPayDomain getWXPayDomain();

    
    public boolean shouldAutoReport() {
        return true;
    }

    
    public int getReportWorkerNum() {
        return 6;
    }


    public int getReportQueueMaxSize() {
        return 10000;
    }

    
    public int getReportBatchSize() {
        return 10;
    }

}
