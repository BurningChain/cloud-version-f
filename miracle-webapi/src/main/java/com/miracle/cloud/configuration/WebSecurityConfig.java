package com.miracle.cloud.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 配置httpBasic认证，配置所有请求都要认证
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}
	
}
