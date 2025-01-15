package com.aluracursos.conversor.client;

import com.aluracursos.conversor.exception.ApiRequestException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateClient {
    private final String baseUrl;
    private final HttpClient httpClient;
    private final Gson gson;

    public ExchangeRateClient(String apiKey) {
        this.baseUrl = "https://v6.exchangerate-api.com/v6/" + "92a3167cb763c6820f1caf48" + "/latest/";
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public JsonObject getExchangeRates(String baseCurrency) {
        String url = baseUrl + baseCurrency;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApiRequestException("Error al obtener las tasas de cambio: " + response.statusCode());
            }
            return gson.fromJson(response.body(), JsonObject.class);
        } catch (IOException | InterruptedException e) {
            throw new ApiRequestException("Error al realizar la solicitud a la API: " + e.getMessage());
        }
    }
}
