package com.aluracursos.conversor.exception;

public class CurrencyNotSupportedException extends RuntimeException {
    public CurrencyNotSupportedException(String message) {

        super(message);
    }
}
