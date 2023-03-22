package com.practice.api.search.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.apikey")
public class ApiKeyConfig {
	private String kakao;
	private String naverId;
	private String naverSecret;
}
