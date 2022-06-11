package ru.itmo.p3131.student18.client.tools.readers;

public class UserDataReader {
    private final int minLoginLength = 6;
    private final int minPasswordLength = 6;

    public String readLogin() {
        String login = "";
        while (login.length() < minLoginLength) {
            System.out.println("Insert your login:");
            login = InputStream.nextLine().trim();
            if (login.length() < minLoginLength) {
                System.out.println("Login length can not be less than " + minLoginLength + ".");
            }
        }
        return login;
    }

    public String readNewPassword() {
        String firstEnteredPassword = readPassword();
        boolean passwordIsCorrect = false;
        while (passwordIsCorrect) {
            System.out.println("Insert the password once again:");
            if (firstEnteredPassword.equals(InputStream.nextLine().trim())) {
                passwordIsCorrect = true;
            }
        }
        return firstEnteredPassword;
    }

    public String readPassword() {
        String password = "";
        while (password.length() < minPasswordLength) {
            System.out.println("Insert your password:");
            password = InputStream.nextLine().trim();
            if ("".equals(password)) {
                System.out.println("Password can not be empty.");
            } else if (password.length() < minPasswordLength) {
                System.out.println("The length of password can not be less than " + minPasswordLength + ".");
            }
        } return password;
    }
}
