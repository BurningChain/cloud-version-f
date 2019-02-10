package com.miracle.cloud.gateway.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RefreshRouteService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RouteLocator locator;

    public void refreshRoute() {

        RoutesRefreshedEvent event = new RoutesRefreshedEvent(locator);
        publisher.publishEvent(event);
    }
}
