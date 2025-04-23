package com.olwethu.olchat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
/**
 * Regex patterns and validation logic developed with assistance from ChatGPT.
 * APA citation: OpenAI. (2025). *ChatGPT* [o4-mini]. Retrieved from https://chat.openai.com
 */

public class Login {
    private final Map<String, User> users = new HashMap<>();

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
        return Pattern.matches(regex, password);
    }

    public boolean checkCellPhoneNumber(String cell) {
        String regex = "^\\+\\d{1,3}\\d{7,10}$";
        return Pattern.matches(regex, cell);
    }

    public String registerUser(String username,
                               String password,
                               String cell,
                               String firstName,
                               String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        users.put(username, new User(username, password, cell, firstName, lastName));
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        User u = users.get(username);
        return u != null && u.password.equals(password);
    }

    public String returnLoginStatus(String username) {
        User u = users.get(username);
        return "Welcome " + u.firstName + ", " + u.lastName + " it is great to see you again.";
    }

    private static class User {

        @SuppressWarnings("unused")
                String username;
        @SuppressWarnings("unused")
                String password;
        @SuppressWarnings("unused")
                String cell;
        String firstName, lastName;
        User(String u, String p, String c, String f, String l) {
            username = u; password = p; cell = c;
            firstName = f; lastName = l;
        }
    }
}
