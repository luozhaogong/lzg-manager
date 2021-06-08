package com.lzg.manager.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.lzg.common.CommonConfiguration;
import com.lzg.common.exception.ExceptionProvider;

@Import(CommonConfiguration.class)
@Configuration
public class CommonConfig {

	@Bean
	public ExceptionProvider exceptionProvider() {
		return () -> Arrays.asList(IllegalArgumentException.class);
	}
}
