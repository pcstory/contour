package com.contour.wallet.coin.exception;

public class BizException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public BizException(CoinErrorEnum error) {
		super(error.getMsg());
	}

}
