package com.conversordemonedas.jokerp515.Main;

import com.conversordemonedas.jokerp515.models.CurrencyConverter;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al conversor de monedas hecho por jokerp515\n\n");

        System.out.println("Inicialmente, prefieres que los decimales que ingreses sean con punto o con coma?");
        System.out.println("1. Punto\n2. Coma");
        System.out.print("Ingrese una opción: ");
        int decimalOption = sc.nextInt();
        sc.nextLine(); // Para que no se salte la siguiente línea
        if (decimalOption == 1) {
            System.out.println("Los decimales serán con punto.");
            sc.useLocale(Locale.UK); // Para que los decimales sean con punto
        } else if (decimalOption == 2) {
            System.out.println("Los decimales serán con coma.");
            sc.useLocale(Locale.GERMANY); // Para que los decimales sean con coma
        } else {
            System.out.println("Opción inválida, se usará punto por defecto.");
            sc.useLocale(Locale.UK); // Para que los decimales sean con punto
        }
        System.out.println("\n");

        while (true) {
            System.out.println(
                "Menú principal\n1. Conversor de USD a otras monedas\n2. Conversor de EUR a otras monedas\n" +
                "3. Conversor de COP a otras monedas\n4. Conversor de MXN a otras monedas\n5. Conversor de CLP a otras monedas\n6. Salir\n");
            System.out.print("Ingrese una opción: ");
            int option = sc.nextInt();

            if (option == 6) {
                System.out.println("Gracias por usar el conversor de monedas hecho por jokerp515");
                break;
            }

            String currencyToConvert = switch (option) {
                case 1 -> "USD";
                case 2 -> "EUR";
                case 3 -> "COP";
                case 4 -> "MXN";
                case 5 -> "CLP";
                default -> throw new IllegalArgumentException("Opción inválida, por favor intente de nuevo\n");
            };

            System.out.println("Ingrese la cantidad de " + currencyToConvert + " a convertir: ");
            double amount = sc.nextDouble();

            System.out.println("¿A qué moneda desea convertir?");
            System.out.println("Opciones: USD, EUR, COP, MXN, CLP");
            System.out.print("Ingrese una opción: ");
            String targetCurrency = sc.next().toUpperCase();
            
            CurrencyConverter converter = new CurrencyConverter(currencyToConvert);

            try {
                double result = converter.convert(currencyToConvert, targetCurrency, amount);
                System.out.printf("La cantidad de %.2f %s es igual a %.2f %s\n\n", amount, currencyToConvert, result, targetCurrency);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }

        sc.close();
    }
}
