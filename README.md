# Conversor de Monedas

Este proyecto es un Conversor de Monedas implementado en **Java 17**, que permite realizar conversiones entre cuatro monedas específicas:  
- Dólar (USD)  
- Peso Colombiano (COP)  
- Peso Argentino (ARS)  
- Real Brasileño (BRL)  

Utiliza la **API de ExchangeRate-API** para obtener tasas de cambio en tiempo real y está diseñado siguiendo las mejores prácticas de programación orientada a objetos.

## Características
- Conversión en tiempo real entre las monedas soportadas.
- Interfaz de línea de comandos (CLI) para interactuar con el usuario.
- Modularidad y escalabilidad mediante una estructura de paquetes bien definida.
- Manejador de errores personalizado para solicitudes a la API y monedas no soportadas.
- Pruebas unitarias incluidas para verificar la funcionalidad del cliente, el servicio de tasas de cambio y la gestión de excepciones.

## Notas
- HttpClientFactory.java: Este archivo será implementado en el futuro para manejar la creación y configuración del cliente HTTP de forma reutilizable, mejorando la eficiencia del código.
- ExchangeRate.java: En la futura implementación, esta clase modelará las tasas de cambio entre las monedas, haciendo que las conversiones sean más fácilmente rastreables y estructuradas.

## Requisitos
- Java 17 o superior.
- IntelliJ IDEA u otro IDE de tu preferencia.
- API Key de ExchangeRate-API.
- Gson 2.10.1.

## Instrucciones de Uso
1. Ejecuta la clase principal **`CurrencyConverterCLI`**.
2. Selecciona una opción del menú para convertir entre las monedas disponibles.
3. Ingresa la cantidad y observa el resultado.

## Contribuciones
¡Las contribuciones son bienvenidas! Si tienes ideas o mejoras, por favor abre un pull request o una issue.
