package com.contour.wallet;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.contour.wallet.coin.CoinRepository;

@SpringBootTest
@Sql({ "/schema.sql", "/data.sql" })
public class H2DBTest {
	@Autowired
	private CoinRepository repo;

	@Test
	public void testLoadDataForTestClass() {
		assertEquals(4L, repo.count());
	}
}
