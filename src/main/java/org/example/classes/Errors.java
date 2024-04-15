package org.example.classes;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private static final List<String> errorsData = new ArrayList<String>();

    public static void addError(Token token, String message) {
        errorsData.add("Line " + token.getLine() + ": " + message);
    }

    public static void printErrors() {
        for (String error : errorsData) {
            System.out.println(error);
        }
    }

    public static void getErrors() {
        for (String error : errorsData) {
            System.out.println(error);
        }
    }

    public static int getErrorsSize() {
        return errorsData.size();
    }
}
