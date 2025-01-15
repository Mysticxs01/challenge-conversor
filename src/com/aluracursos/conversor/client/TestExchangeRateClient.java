package com.aluracursos.conversor.client;

import com.google.gson.JsonObject;

public class TestExchangeRateClient {
    public static void main(String[] args) {
        String apiKey = "92a3167cb763c6820f1caf48";
        ExchangeRateClient client = new ExchangeRateClient(apiKey);

        try {
            JsonObject rates = client.getExchangeRates("USD");

            double rateCOP = rates.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
            double rateBRL = rates.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
            double rateARS = rates.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();

            System.out.println("COP: " + rateCOP);
            System.out.println("BRL: " + rateBRL);
            System.out.println("ARS: " + rateARS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
