package com.contour.wallet.coin.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.contour.wallet.coin.controller.CoinDto;
import com.contour.wallet.coin.dao.Coin;
import com.contour.wallet.coin.exception.BizException;
import com.contour.wallet.coin.exception.CoinErrorEnum;

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
			throw new BizException(CoinErrorEnum.INVALID_INPUT);
		}
		String[] coinArr = coins.split(",");
		List<Integer> listArr = new ArrayList<>();
		try { 
			for (String coin : coinArr)
				listArr.add(Integer.parseInt(coin.trim()));
		} catch (NumberFormatException e) {
			throw new BizException(CoinErrorEnum.INVALID_INPUT);
		}
		return listArr;
	}
}
