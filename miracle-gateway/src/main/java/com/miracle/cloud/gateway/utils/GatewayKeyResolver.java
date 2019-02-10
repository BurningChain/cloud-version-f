package com.miracle.cloud.gateway.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class GatewayKeyResolver implements KeyResolver {

    private static final String KEY = "gatewayKeyResolver";
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {

        return Mono.just(getKey(exchange));
    }

    private String getKey(ServerWebExchange exchange) {

        LimitKey limitKey = new LimitKey();

        limitKey.setApi(exchange.getRequest().getPath().toString());
        limitKey.setBiz(exchange.getRequest().getHeaders().getFirst("biz"));

        return JSON.toJSONString(limitKey);
    }
}
