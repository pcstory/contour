package com.contour.wallet.coin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wallet")
public class CoinController {

	@Autowired
	private CoinHelper helper;

	@Autowired
	private CoinService service;

	private final Logger log = LoggerFactory.getLogger(CoinController.class);

	@GetMapping("/health")
	public String greeting(
			@RequestParam(value = "testMsg", defaultValue = "Use Test as param to test input") String testMsg) {
		return testMsg;
	}

	@GetMapping("/")
	public List<CoinDto> getAllCoins() {
		return getAllCoinsDto();
	}

	private List<CoinDto> getAllCoinsDto() {
		List<Coin> coins = service.getAllCoins();
		log.debug(coins.size() + "");
		return coins.stream().map(helper::convertDto).collect(Collectors.toList());
	}

	@GetMapping("/pay")
	public List<CoinDto> pay(@RequestParam String coins) throws Exception {
		String[] coinArr = coins.split(",");
		List<Integer> listArr = new ArrayList<>();

		for (String coin : coinArr)
			listArr.add(Integer.parseInt(coin));

		List<Coin> coinList = service.pay(listArr);
		return coinList.stream().map(helper::convertDto).collect(Collectors.toList());
	}
	
	@GetMapping("/put")
	public List<CoinDto> put(@RequestParam String coins) throws Exception {
		String[] coinArr = coins.split(",");
		List<Integer> listArr = new ArrayList<>();

		for (String coin : coinArr)
			listArr.add(Integer.parseInt(coin));

		List<Coin> coinList = service.put(listArr);
		return coinList.stream().map(helper::convertDto).collect(Collectors.toList());
	}
	

	
}
