package com.olwethu.olchat;

import java.util.Scanner;

public class ChatApp {
    private static final String USER_SUCCESS = 
        "Username successfully captured.";
    private static final String USER_FAIL = 
        "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

    private static final String PASS_SUCCESS = 
        "Password successfully captured.";
    private static final String PASS_FAIL = 
        "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

    private static final String CELL_SUCCESS = 
        "Cell phone number successfully added.";
    private static final String CELL_FAIL = 
        "Cell phone number incorrectly formatted or does not contain international code.";

    private static final String REGISTER_SUCCESS = 
        "User registered successfully.";

    private static final Login loginService = new Login();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== ChatApp ===");
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1" -> doRegister(in);
                case "2" -> doLogin(in);
                case "3" -> {
                    System.out.println("Goodbye!");
                    in.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void doRegister(Scanner in) {
        System.out.print("First name: ");
        String first = in.nextLine().trim();
        System.out.print("Last name: ");
        String last = in.nextLine().trim();
        System.out.print("Username: ");
        String user = in.nextLine().trim();
        System.out.print("Password: ");
        String pass = in.nextLine();
        System.out.print("Cell (+countrycode): ");
        String cell = in.nextLine().trim();

        // Run each validation separately:
        boolean uOk = loginService.checkUserName(user);
        System.out.println(uOk ? USER_SUCCESS : USER_FAIL);

        boolean pOk = loginService.checkPasswordComplexity(pass);
        System.out.println(pOk ? PASS_SUCCESS : PASS_FAIL);

        boolean cOk = loginService.checkCellPhoneNumber(cell);
        System.out.println(cOk ? CELL_SUCCESS : CELL_FAIL);

        // Only if *all* passed do we store and show final confirmation:
        if (uOk && pOk && cOk) {
            // this will add the user to the in-memory store
            loginService.registerUser(user, pass, cell, first, last);
            System.out.println(REGISTER_SUCCESS);
        }
    }

    private static void doLogin(Scanner in) {
        System.out.print("Username: ");
        String user = in.nextLine().trim();
        System.out.print("Password: ");
        String pass = in.nextLine();

        if (loginService.loginUser(user, pass)) {
            System.out.println(loginService.returnLoginStatus(user));
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}
