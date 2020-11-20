package com.contour.wallet.coin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long>{

	@Query("SELECT SUM(c.value) FROM Coin c")
	Integer getSum();
	
	List<Coin> findByValueGreaterThan(int value);
}
