package com.wup.cc.constants;

public enum ExchangeRateConstant {
    BUYING_RATE(1), EXCHANGE_RATE(2);

    private int value;

    private ExchangeRateConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}