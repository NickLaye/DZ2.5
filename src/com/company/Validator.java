package com.company;

import java.util.Objects;

public class Validator {

    private static final String VALID_SYMBOLS = "abcdefghijklmnoprstuvwxyzABCDEFGHIGKLMNOPRSTUVWXYZ0123456789_";


    private Validator() {

    }

    public static boolean check(String login,
                                String password,
                                String confirmPassword) {
        try {
            validate(login, password, confirmPassword);
            return true;
        } catch (WrongLoginException | WrongPasswordException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    private static void validate(String login,
                                 String password,
                                 String confirmPassword) throws WrongLoginException, WrongPasswordException {
        if (Objects.isNull(login) || login.length() > 20 || !containsValidSymbols(login)) {
            throw new WrongLoginException("Длина логина должна быть <= 20 символов");
        }
        if (Objects.isNull(password) || password.length() >= 20 ||
                Objects.isNull(confirmPassword) || confirmPassword.length() >= 20) {
            throw new WrongPasswordException("Длина пароля должна быть < 20 символов");
        }
        if (!containsValidSymbols(password)) {
            System.out.println("Логин содержит невалидные символы");
        }
        if (!containsValidSymbols(confirmPassword)) {
            System.out.println("Пароль содержит невалидные символы");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли должны совпадать!");
        }
    }

    private static boolean containsValidSymbols(String s) {
        char[] symbols = s.toCharArray();
        for (char symbol : symbols) {
            if (!VALID_SYMBOLS.contains(String.valueOf(symbol))) {
                return false;
            }
        }
        return true;
    }
}
