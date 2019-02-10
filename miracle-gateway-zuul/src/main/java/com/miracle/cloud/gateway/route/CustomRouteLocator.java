package com.miracle.cloud.gateway.route;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.ZuulProperties.*;

public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRouteLocator.class);

    private JdbcTemplate jdbcTemplate;
    private ZuulProperties properties;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        LOGGER.info("serveletPath:{}", servletPath);
    }

    /**
     * 父类已经提供了这个方法
     */
    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulRoute> locateRoutes() {

        LinkedHashMap<String, ZuulRoute> routeLinkedHashMap = new LinkedHashMap<>();

        routeLinkedHashMap.putAll(super.locateRoutes());
        routeLinkedHashMap.putAll(locateRoutesFromDB());

        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routeLinkedHashMap.entrySet()) {
            String path = entry.getKey();

            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (org.springframework.util.StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }

        return values;
    }

    private Map<String, ZuulRoute> locateRoutesFromDB() {

        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteVO> routeVOS = jdbcTemplate.query("select * from gateway_api_define where enbaled = ture", new BeanPropertyRowMapper<>(ZuulRouteVO.class));
        for (ZuulRouteVO routeVO : routeVOS) {
            if (StringUtils.isBlank(routeVO.getPath()) || StringUtils.isBlank(routeVO.getUrl())) {
                continue;
            }
            ZuulRoute zuulRoute = new ZuulRoute();

            try {
                BeanUtils.copyProperties(routeVO, zuulRoute);
            } catch (BeansException e) {
                e.printStackTrace();
                LOGGER.error("-------------load zuul route info from db with error ----------------------");
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }

    public static class ZuulRouteVO {

        private String id;

        private String path;

        private String serviceId;

        private String url;

        private boolean stripPrefix = true;

        private boolean retryable;

        private String apiName;

        private boolean enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isStripPrefix() {
            return stripPrefix;
        }

        public void setStripPrefix(boolean stripPrefix) {
            this.stripPrefix = stripPrefix;
        }

        public boolean isRetryable() {
            return retryable;
        }

        public void setRetryable(boolean retryable) {
            this.retryable = retryable;
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

}
