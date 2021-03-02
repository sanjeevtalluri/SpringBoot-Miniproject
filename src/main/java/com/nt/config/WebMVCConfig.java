package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebMVCConfig {
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource getMS() {
		ResourceBundleMessageSource ms=new ResourceBundleMessageSource();
		ms.setBasename("com/nt/commons/validation");
		return ms;
	}

}
