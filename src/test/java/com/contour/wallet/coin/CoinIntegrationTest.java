package com.contour.wallet.coin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.contour.wallet.coin.dao.CoinRepository;
import com.contour.wallet.coin.exception.CoinErrorEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CoinIntegrationTest {
	
	private final Logger Log = LoggerFactory.getLogger(CoinIntegrationTest.class);
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private CoinRepository repo;
	
	@Test
	public void test1GreetingShouldReturnDefaultMessage() throws Exception {
		String testStr = "echo";
		assertThat(
				this.restTemplate.getForObject("http://localhost:" + port + "/wallet/health/" + testStr, String.class))
						.contains(testStr);
	}

	@Test
	public void test2PayInvalidInput() throws Exception {
		String output = this.restTemplate.getForObject("http://localhost:" + port + "/wallet/pay?coins=a",
				String.class);
		assertThat(output, containsString(CoinErrorEnum.INVALID_INPUT.getMsg()));
	}

	@Test
	public void test3PayInsufficient() throws Exception {
		String output = this.restTemplate.getForObject("http://localhost:" + port + "/wallet/pay?coins=8",
				String.class);
		assertThat(output, containsString(CoinErrorEnum.INSUFFICIENT_FUNDS.getMsg()));
	}

	@Test
	public void testPay() throws Exception {
		String output = this.restTemplate.getForObject("http://localhost:" + port + "/wallet/pay?coins=5",
				String.class);
		assertTrue(output.equals("[{\"value\":2}]") || output.equals("[{\"value\":1},{\"value\":1}]"));
	}



}
