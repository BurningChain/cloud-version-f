package com.miracle.cloud.gateway.utils;

public class LimitKey {

    private String api;
    private String biz;

    public LimitKey() {

    }

    public LimitKey(String api, String biz) {
        this.api = api;
        this.biz = biz;
    }

    @Override
    public String toString() {
        return "LimitKey{" +
                "api='" + api + '\'' +
                ", biz='" + biz + '\'' +
                '}';
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }
}
