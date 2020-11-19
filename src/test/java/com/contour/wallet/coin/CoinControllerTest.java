package com.contour.wallet.coin;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CoinControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		String testStr = "echo";
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/wallet/health/" + testStr,
				String.class)).contains(testStr);
	}
	
	@Test
	public void testExactPay() throws Exception {
		String output = this.restTemplate.getForObject("http://localhost:" + port + "/wallet/pay/coins=1,3", String.class);
		System.out.println(output);
	}
}
