package com.conversordemonedas.jokerp515.requests;

import com.conversordemonedas.jokerp515.configurations.ApiKeyManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private static ApiRequest instance; // Singleton
    private String apiKey;
    private Map<String, Map<String, Double>> exchangeRates; // Almacena todas las tasas

    private ApiRequest() {
        this.apiKey = ApiKeyManager.getInstance().getApiKey();
        this.exchangeRates = new HashMap<>();
        loadExchangeRates(); // Carga las tasas al inicio
    }

    public static ApiRequest getInstance() {
        if (instance == null) {
            instance = new ApiRequest();
        }
        return instance;
    }

    private void loadExchangeRates() {
        // Monedas soportadas
        String[] currencies = {"USD", "EUR", "COP", "MXN", "CLP"};
        for (String currency : currencies) {
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + currency;
            try {
                Map<String, Double> rates = fetchRatesFromApi(url);
                if (rates != null) {
                    exchangeRates.put(currency, rates);
                }
            } catch (Exception e) {
                System.err.println("Error al cargar tasas para " + currency + ": " + e.getMessage());
            }
        }
    }

    private Map<String, Double> fetchRatesFromApi(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        // Verifies if the API request was successful
        if (!"success".equals(jsonObject.get("result").getAsString())) {
            throw new Exception("Error en la API: " + jsonObject.get("result").getAsString());
        }

        // Obtén las tasas de cambio del objeto "conversion_rates"
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        Map<String, Double> rates = new HashMap<>();
        for (String key : conversionRates.keySet()) {
            rates.put(key, conversionRates.get(key).getAsDouble());
        }

        return rates;
    }

    public Map<String, Double> getRates(String currency) {
        return exchangeRates.get(currency);
    }
}
