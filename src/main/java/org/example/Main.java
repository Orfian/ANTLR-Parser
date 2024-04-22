package org.example;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.example.classes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        ParseTree tree = parser.program();

        System.out.println(tree.toStringTree(parser));

        if (parser.getNumberOfSyntaxErrors() == 0) {
            System.out.println("The input is a valid LG program.");

            TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
            typeCheckVisitor.visit(tree);

            if (Errors.getErrorsSize() > 0) {
                Errors.printErrors();
                return;
            }

            CompilerVisitor compilerVisitor = new CompilerVisitor(typeCheckVisitor.getTypes());
            List<Instruction> instructions = compilerVisitor.visit(tree);

            for (Instruction instruction : instructions) {
                System.out.println(instruction);
            }

            System.out.println("-----------------------------------------");
            Scanner scanner = new Scanner(System.in);
            Processor processor = new Processor(instructions, scanner);
        } else {
            System.out.println("The input is not a valid LG program.");
        }
    }
}