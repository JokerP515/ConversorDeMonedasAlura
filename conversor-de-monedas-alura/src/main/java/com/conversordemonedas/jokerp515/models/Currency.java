package com.conversordemonedas.jokerp515.models;

import com.conversordemonedas.jokerp515.requests.ApiRequest;
import java.util.Map;

public class Currency {
    private String name;
    private Map<String, Double> rates;

    public Currency(String name) {
        this.name = name;
        this.rates = ApiRequest.getInstance().getRates(name); // Gets currencies from ApiRequest
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public Double getRateFor(String targetCurrency) {
        if (rates != null) {
            return rates.get(targetCurrency);
        }
        return null;
    }
}
