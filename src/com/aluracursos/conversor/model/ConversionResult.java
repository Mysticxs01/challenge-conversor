package com.aluracursos.conversor.model;

public record ConversionResult(
        double initialAmount,
        Currency fromCurrency,
        Currency toCurrency,
        double convertedAmount) { }
