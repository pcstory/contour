package com.contour.wallet.coin;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CoinServiceTest {

	@Autowired
	private CoinService coinService;
	
	@Test
	public void testExactPay() {
//		Coin one = new Coin(1);
//		Coin three = new Coin(3);
//		List<Coin> list = new ArrayList<>();
//		list.add(one);
//		list.add(three);
//		coinService.pay(list);
	}
}
