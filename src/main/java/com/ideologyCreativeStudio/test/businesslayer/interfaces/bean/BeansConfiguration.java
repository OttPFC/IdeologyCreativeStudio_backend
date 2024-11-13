package com.ideologyCreativeStudio.test.businesslayer.interfaces.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Configuration
public class BeansConfiguration {

	@Bean
	public Pageable defaultPageable() {
		return PageRequest.of(0, 10);
	}
}
