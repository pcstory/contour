package com.contour.wallet.coin.service;

import java.util.List;

import com.contour.wallet.coin.dao.Coin;

public interface ICoinService {

	List<Coin> pay(List<Integer> coins) throws Exception;

	List<Coin> put(List<Integer> listArr);

	List<Coin> getAllCoins();

}
