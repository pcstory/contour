package com.contour.wallet.coin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CoinRepoTest {

	@Autowired
	private CoinRepository repo;
	
	@Test
	public void findByValue() {
//		List<Coin> oneCoin = repo.findByValue(1);
//		assertTrue(oneCoin.size() > 0);
	}
	
	public static void main(String[] s) {
		Coin c1 = new Coin(1);
		Coin c2 = new Coin(1);
		System.out.println(c1 == c2);
		System.out.println(c1.equals(c2));
	}
}
