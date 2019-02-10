package com.miracle.cloud.gateway.utils;


import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;

import java.util.Map;

public class LimitConfig {

    private Map<String, RedisRateLimiter.Config> tokenConfig;
    private String routeId;

    public LimitConfig() {

    }

    public Map<String, RedisRateLimiter.Config> getTokenConfig() {
        return tokenConfig;
    }

    public void setTokenConfig(Map<String, RedisRateLimiter.Config> tokenMap) {
        this.tokenConfig = tokenMap;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "LimitConfig{" +
                "tokenMap=" + tokenConfig +
                ", routeId='" + routeId + '\'' +
                '}';
    }

    public LimitConfig(Map<String, RedisRateLimiter.Config> tokenMap, String routeId) {
        this.tokenConfig = tokenMap;
        this.routeId = routeId;
    }
}
