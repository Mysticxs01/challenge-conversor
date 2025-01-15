package com.aluracursos.conversor.service;

import com.aluracursos.conversor.client.ExchangeRateClient;

public class TestExchangeRateService {
    public static void main(String[] args) {
        String apiKey = "92a3167cb763c6820f1caf48";
        ExchangeRateClient client = new ExchangeRateClient(apiKey);
        ExchangeRateService service = new ExchangeRateService(client);

        try {
            double result = service.convert("USD", "COP", 100);
            System.out.println("Resultado de la conversión: 100 USD = " + result + " COP");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
