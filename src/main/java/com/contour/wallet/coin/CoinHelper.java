package com.contour.wallet.coin;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoinHelper {

	@Autowired
	private ModelMapper modelMapper;
	
	public CoinDto convertDto(Coin coin) {
	    CoinDto coinDto = modelMapper.map(coin, CoinDto.class);
	    return coinDto;
	}
	
	public Coin getNewCoin(int value) {
	    return new Coin(value);
	}

	public List<Integer> parseInput(String coins) throws BizException{
		if (coins != null && coins.contains("-")) {
			throw new BizException("Invalid Input for coins. Negative value is not acceptable");
		}
		String[] coinArr = coins.split(",");
		List<Integer> listArr = new ArrayList<>();
		try { 
			for (String coin : coinArr)
				listArr.add(Integer.parseInt(coin.trim()));
		} catch (NumberFormatException e) {
			throw new BizException("Invalid Input for coins");
		}
		return listArr;
	}
}
