package com.contour.wallet.coin;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanFactory {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
