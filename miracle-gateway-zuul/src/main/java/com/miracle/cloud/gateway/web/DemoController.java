package com.miracle.cloud.gateway.web;


import com.miracle.cloud.gateway.event.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {


    @Autowired
    private ZuulHandlerMapping handlerMapping;

    @Autowired
    private RefreshRouteService refreshRouteService;


    @GetMapping("/refresh")
    public String refreshRoute() {
        refreshRouteService.refreshRoute();
        return "refreshRoute";
    }

    @GetMapping("/watchNowRoute")
    public String watchNowRoute() {

        Map<String, Object> handlerMap = handlerMapping.getHandlerMap();
        return "watchNowRoute";
    }
}


