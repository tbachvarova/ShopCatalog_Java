package com.CITB408_2021.Exeptions;

import com.CITB408_2021.Client;

import java.math.BigDecimal;

public class NotEnoughMoneyToPay extends Exception {
    private Client client;
    private BigDecimal notEnoughMoney;

    public NotEnoughMoneyToPay(Client client, BigDecimal notEnoughMoney) {
        this.client = client;
        this.notEnoughMoney = notEnoughMoney;
    }

    @Override
    public String toString() {
        return "NotEnoughMoneyToPay{ На клиента: " +client.getName() + " не му достигат: "+ notEnoughMoney + " пари! }";
    }
}
