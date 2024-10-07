package com.qwerpark.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.qwerpark.memo.common.FileManager;
import com.qwerpark.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
	}
	
	@Override 
	public void addInterceptors(InterceptorRegistry registry) {
		PermissionInterceptor intercpetor = new PermissionInterceptor();
		registry.addInterceptor(intercpetor)
		.addPathPatterns("/**")
		.excludePathPatterns("/static/**", "/images/**", "/user/logout");
	}
}
