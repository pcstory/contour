package com.contour.wallet.coin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long>{

	List<Coin> findByValueIn(List<Integer> value);
	
	List<Coin> findByValue(int value);
	
	@Query("SELECT SUM(c.value) FROM Coin c")
	int getSum();
	
	List<Coin> findByValueGreaterThan(int value);
}
