package com.contour.wallet.coin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long>{

	Coin findFirstByValue(int value);
	
	List<Coin> findByValue(int value);
	
	List<Coin> findByValueGreaterThan(int value);
}
