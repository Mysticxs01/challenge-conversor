package com.aluracursos.conversor.exception;

import com.aluracursos.conversor.client.ExchangeRateClient;
import com.aluracursos.conversor.service.ExchangeRateService;

public class TestExceptions {
    public static void main(String[] args) {
        String apiKey = "92a3167cb763c6820f1caf48";
        ExchangeRateClient client = new ExchangeRateClient(apiKey);
        ExchangeRateService service = new ExchangeRateService(client);

        try {
            double result = service.convert("USD", "XYZ", 100);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
