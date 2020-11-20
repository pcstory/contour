package com.contour.wallet.coin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contour.wallet.coin.dao.Coin;
import com.contour.wallet.coin.dao.CoinRepository;
import com.contour.wallet.coin.exception.BizException;
import com.contour.wallet.coin.exception.CoinErrorEnum;
import com.contour.wallet.coin.util.CoinHelper;

@Service
public class CoinService implements ICoinService{

	@Autowired
	private CoinRepository coinRepo;

	@Autowired
	private CoinHelper helper;

	private static final Logger log = LoggerFactory.getLogger(CoinService.class);

	@Override
	public List<Coin> getAllCoins() {
		return coinRepo.findByValueGreaterThan(0);
	}

	@Override
	public List<Coin> pay(List<Integer> coins) throws Exception {
		Integer sumObj = coinRepo.getSum();
		int sumInDB =  sumObj == null ? 0: sumObj.intValue();
		Integer sum = coins.stream().reduce(0, Integer::sum);
		if (sumInDB < sum) {
			throw new BizException(CoinErrorEnum.INSUFFICIENT_FUNDS);
		}
		processPay(coins);
		log.debug(coinRepo.findAll().toString());
		return findAllInList();
	}

	private List<Coin> findAllInList() {
		return coinRepo.findByValueGreaterThan(0);
	}

	private void processPay(List<Integer> coins) throws Exception {
		List<Coin> toBeDelete = new ArrayList<>();
		Integer sum = coins.stream().reduce(0, Integer::sum);
		Iterator<Coin> ite = coinRepo.findAll().iterator();
		int payTotal = 0;
		while (ite.hasNext()) {
			Coin coin = ite.next();
			payTotal += coin.getValue();
			ite.remove();
			toBeDelete.add(coin);
			if (payTotal >= sum) {
				log.debug("paid All");
				if (payTotal - sum > 0) {
					log.debug("save remainint");
					Coin remain = new Coin(payTotal - sum);
					coinRepo.save(remain);
				}
				break;
			}
		}
		coinRepo.deleteAll(toBeDelete);
	}

	@Override
	public List<Coin> put(List<Integer> listArr) {
		List<Coin> coins = listArr.stream().map(helper::getNewCoin).collect(Collectors.toList());
		coinRepo.saveAll(coins);
		return findAllInList();
	}

}
