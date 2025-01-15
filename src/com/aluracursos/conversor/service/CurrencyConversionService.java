package com.aluracursos.conversor.service;

import com.aluracursos.conversor.model.Currency;
import com.aluracursos.conversor.model.ConversionResult;
import com.aluracursos.conversor.exception.CurrencyNotSupportedException;

import java.util.List;

public class CurrencyConversionService {

    private final ExchangeRateService exchangeRateService;

    public CurrencyConversionService(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public List<Currency> getSupportedCurrencies() {
        return List.of(
                new Currency("USD", "Dólar Estadounidense"),
                new Currency("COP", "Peso Colombiano"),
                new Currency("ARS", "Peso Argentino"),
                new Currency("BRL", "Real Brasileño")
        );
    }

    public ConversionResult convert(double amount, Currency from, Currency to) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad a convertir debe ser mayor a 0.");
        }

        List<Currency> supportedCurrencies = getSupportedCurrencies();
        if (!supportedCurrencies.contains(from) || !supportedCurrencies.contains(to)) {
            throw new CurrencyNotSupportedException("Una de las monedas no es soportada.");
        }

        double convertedAmount = exchangeRateService.convert(from.code(), to.code(), amount);
        return new ConversionResult(amount, from, to, convertedAmount);
    }
}
