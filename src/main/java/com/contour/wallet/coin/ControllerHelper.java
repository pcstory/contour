package com.contour.wallet.coin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerHelper {

	@Autowired
	private ModelMapper modelMapper;
	
	public CoinDto convertDto(Coin coin) {
	    CoinDto coinDto = modelMapper.map(coin, CoinDto.class);
	    return coinDto;
	}
	
}
