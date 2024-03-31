package org.example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CharStream input = null;
        try {
            input = CharStreams.fromFileName("src/main/antlr/input.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        lgLexer lexer = new lgLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        lgParser parser = new lgParser(tokens);

        System.out.println(parser.program().toStringTree(parser));

        if (parser.getNumberOfSyntaxErrors() == 0) {
            System.out.println("The input is a valid LG program.");
        } else {
            System.out.println("The input is not a valid LG program.");
        }
    }
}