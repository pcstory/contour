package com.contour.wallet.coin.exception;

public enum CoinErrorEnum {

	INSUFFICIENT_FUNDS(-1, "Insufficient fund"),
	INVALID_INPUT(-2, "Invalid Input Detected");
	
	private String msg;
	
	private int errorCode;
	
	CoinErrorEnum(int errorCode, String msg) {
		this.msg = msg;
		this.errorCode = errorCode;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
}
