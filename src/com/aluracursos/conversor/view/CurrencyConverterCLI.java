package com.aluracursos.conversor.view;

import com.aluracursos.conversor.exception.CurrencyNotSupportedException;
import com.aluracursos.conversor.model.Currency;
import com.aluracursos.conversor.model.ConversionResult;
import com.aluracursos.conversor.service.CurrencyConversionService;
import com.aluracursos.conversor.service.ExchangeRateService;
import com.aluracursos.conversor.client.ExchangeRateClient;

import java.util.Scanner;

public class CurrencyConverterCLI {

    private final CurrencyConversionService currencyConversionService;

    public CurrencyConverterCLI(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    convertCurrency(scanner);
                    break;
                case 2:
                    System.out.println("Saliendo del programa...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n*** Conversor de Monedas ***");
        System.out.println("1) Convertir Moneda");
        System.out.println("2) Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void convertCurrency(Scanner scanner) {
        try {
            System.out.println("Seleccione la moneda de origen:");
            Currency[] supportedCurrencies = currencyConversionService.getSupportedCurrencies().toArray(new Currency[0]);

            for (int i = 0; i < supportedCurrencies.length; i++) {
                System.out.println((i + 1) + ") " + supportedCurrencies[i].name());
            }

            System.out.print("Ingrese el número de la moneda de origen: ");
            int fromCurrencyIndex = scanner.nextInt() - 1;

            System.out.print("Ingrese el número de la moneda de destino: ");
            int toCurrencyIndex = scanner.nextInt() - 1;

            System.out.print("Ingrese la cantidad a convertir: ");
            double amount = scanner.nextDouble();

            Currency fromCurrency = supportedCurrencies[fromCurrencyIndex];
            Currency toCurrency = supportedCurrencies[toCurrencyIndex];

            ConversionResult result = currencyConversionService.convert(amount, fromCurrency, toCurrency);
            System.out.println("Resultado: " + amount + " " + fromCurrency.code() + " = " + result.convertedAmount() + " " + toCurrency.code());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Selección inválida de moneda.");
        } catch (CurrencyNotSupportedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ExchangeRateClient exchangeRateClient = new ExchangeRateClient("92a3167cb763c6820f1caf48");
        ExchangeRateService exchangeRateService = new ExchangeRateService(exchangeRateClient);
        CurrencyConversionService currencyConversionService = new CurrencyConversionService(exchangeRateService);

        CurrencyConverterCLI converterCLI = new CurrencyConverterCLI(currencyConversionService);
        converterCLI.start();
    }
}
