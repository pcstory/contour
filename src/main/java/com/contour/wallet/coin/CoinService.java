package com.contour.wallet.coin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

	@Autowired
	private CoinRepository coinRepo;

	private static final Logger log = LoggerFactory.getLogger(CoinService.class);

	public List<Coin> getAllCoins() {
		return coinRepo.findByValueGreaterThan(0);
	}

	public List<Coin> pay(List<Integer> coins) throws Exception {
		exactMatch(coins);
		if (coins.size() > 0) {
			solveRemaining(coins);
		}
		log.debug(coinRepo.findAll().toString());
		return findAllInList();
	}

	private List<Coin> findAllInList() {
		List<Coin> coinList = new ArrayList<>();
		coinRepo.findAll().forEach(coinList::add);
		return coinList;
	}

	private void solveRemaining(List<Integer> coins) throws Exception {
		Integer sum = coins.stream().reduce(0, Integer::sum);
		Iterator<Coin> ite = coinRepo.findAll().iterator();
		int payTotal = 0;
		boolean paidAll = false;
		while (ite.hasNext()) {
			Coin coin = ite.next();
			payTotal += coin.getValue();
			ite.remove();
			coinRepo.delete(coin);
			if (payTotal == sum) {
				log.debug("paid All");
				break;
			}
			if (payTotal > sum) {
				Coin remain = new Coin(payTotal - sum);
				coinRepo.save(remain);
				paidAll = true;
				break;
			}
		}
		if (!paidAll) {
			throw new Exception("Not sufficient funds");
		}
	}

	private void exactMatch(List<Integer> coins) {
		Iterator<Integer> ite = coins.iterator();
		while (ite.hasNext()) {
			int coin = ite.next();
			log.debug("find coin " + coin);
			Coin dbCoin = coinRepo.findFirstByValue(coin);
			if (dbCoin != null) {
				coinRepo.delete(dbCoin);
				ite.remove();
			}
		}
	}

}
