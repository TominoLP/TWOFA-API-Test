package de.tomino;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, String> userMap = new HashMap<>();

    public static void main(String[] args) {
        boolean running = true;
        final Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Select an option:");
            System.out.println("1. Exit");
            System.out.println("2. Add new user");
            System.out.println("3. Validate user");
            System.out.print("Option: ");

            final int option = scanner.nextInt();
            switch (option) {
                case 1 -> running = false;
                case 2 -> addUser(scanner);
                case 3 -> validateUser(scanner);

                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addUser(Scanner scanner) {
        System.out.print("Account: ");
        String accountName = scanner.next();

        System.out.print("issuer: ");
        String issuer = scanner.next();

        if (userMap.containsKey(accountName)) {
            System.out.println("User already exists.");
            return;
        }

        final String code = AuthSys.generateSecretKey();
        userMap.put(accountName, code);
        Utils.openQrCodeWindow(code, issuer, accountName);
    }

    private static void validateUser(Scanner scanner) {
        System.out.println("Select a user:");
        userMap.keySet().forEach(System.out::println);

        System.out.print("User: ");
        final String account = scanner.next();

        System.out.print("Code: ");
        final String code = scanner.next();

        final boolean isValid = AuthSys.validateCode(userMap.get(account), code);
        if (isValid) {
            System.out.println("\nCode is valid!\n\n\n");
        } else {
            System.out.println("\nCode is not valid.\n\n\n");
        }
    }

}