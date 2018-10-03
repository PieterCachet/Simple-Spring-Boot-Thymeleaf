package com.abcinsurance.abcInsurance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author Pieterv
 *
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/",
				"classpath:/BOOT-INF/classes/static/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/",
				"classpath:/BOOT-INF/classes/static/img/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/",
				"classpath:/BOOT-INF/classes/static/js/");
	}

}