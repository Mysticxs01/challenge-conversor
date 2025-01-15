package com.aluracursos.conversor.service;

import com.aluracursos.conversor.client.ExchangeRateClient;
import com.aluracursos.conversor.exception.CurrencyNotSupportedException;
import com.google.gson.JsonObject;

import java.util.Map;

public class ExchangeRateService {
    private final ExchangeRateClient exchangeRateClient;

    public ExchangeRateService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        JsonObject exchangeRates = exchangeRateClient.getExchangeRates(fromCurrency);

        if (!exchangeRates.has("conversion_rates")) {
            throw new CurrencyNotSupportedException("La moneda base no es soportada: " + fromCurrency);
        }

        JsonObject conversionRates = exchangeRates.getAsJsonObject("conversion_rates");
        if (!conversionRates.has(toCurrency)) {
            throw new CurrencyNotSupportedException("La moneda de destino no es soportada: " + toCurrency);
        }

        double rate = conversionRates.get(toCurrency).getAsDouble();
        return amount * rate;
    }

    public Map<String, Double> getConversionRates(String baseCurrency) {
        JsonObject exchangeRates = exchangeRateClient.getExchangeRates(baseCurrency);

        if (!exchangeRates.has("conversion_rates")) {
            throw new CurrencyNotSupportedException("La moneda base no es soportada: " + baseCurrency);
        }

        JsonObject conversionRates = exchangeRates.getAsJsonObject("conversion_rates");
        return conversionRates.entrySet().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().getAsDouble()
                        )
                );
    }
}
