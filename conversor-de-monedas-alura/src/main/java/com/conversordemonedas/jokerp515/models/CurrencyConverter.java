package com.conversordemonedas.jokerp515.models;

public class CurrencyConverter {
    Currency currency;
    
    public CurrencyConverter(String currencyName) {
        this.currency = new Currency(currencyName);
    }

    public double convert(String currencyToConvert, String targetCurrency, double amount) {
        if (currencyToConvert.equals(targetCurrency)) {
            return amount;
        }

        Double rate = currency.getRateFor(targetCurrency);
        if (rate == null) {
            throw new IllegalArgumentException("Moneda no soportada");
        }

        return amount * rate;
    }
    
}
