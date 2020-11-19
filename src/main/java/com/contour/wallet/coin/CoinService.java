package com.contour.wallet.coin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
		int sumInDB = coinRepo.getSum();
		Integer sum = coins.stream().reduce(0, Integer::sum);
		if (sumInDB < sum) {
			throw new Exception("not sufficient sum");
		}
		
		// Optional - exact match - reduce db operation (Optimization) 
		List<Coin> exactMatchCoins = coinRepo.findByValueIn(coins);
		List<Integer> deletedCoins = exactMatchCoins.stream().map(s -> s.getValue()).collect(Collectors.toList());
		coins.removeAll(deletedCoins);
		coinRepo.deleteAll(exactMatchCoins);
		
		// Solve remaining
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

}
