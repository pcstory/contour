package com.contour.wallet.coin;

public class InsufficientfundException extends Exception{

	private static final long serialVersionUID = 1L;

	public InsufficientfundException(String string) {
		super(string);
	}

}
