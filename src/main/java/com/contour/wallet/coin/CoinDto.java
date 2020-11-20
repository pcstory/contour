package com.contour.wallet.coin;

public class CoinDto {

	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return (value != 0 ? Integer.toString(value) : " ");
	}
	
}
