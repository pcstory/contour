package com.contour.wallet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.contour.wallet.coin.CoinController;

@SpringBootTest
class ApplicationTest {

	@Autowired
	private CoinController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
