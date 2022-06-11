package ru.itmo.p3131.student18.client.tools.readers;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputStream {
    private static Scanner inputScanner;

    private InputStream() {
        inputScanner = new Scanner(System.in);
    }

    public static Scanner getInstance() {
        if (inputScanner == null) {
            new InputStream();
        } return inputScanner;
    }
    public static String nextLine() {
        getInstance();
        String nextLine = "";
        try {
            nextLine = inputScanner.nextLine();
        } catch (NoSuchElementException e) {
            System.exit(1);
        } return nextLine;
    }

}
