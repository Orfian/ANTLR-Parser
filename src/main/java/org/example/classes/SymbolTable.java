package org.example.classes;

import java.util.HashMap;
import org.antlr.v4.runtime.Token;

public class SymbolTable {
    private final HashMap<String, Type> symbols = new HashMap<String, Type>();

    public void addSymbol(Token token, Type type) {
        if (symbols.containsKey(token.getText())) {
            Errors.addError(token, "Symbol " + token.getText() + " already declared.");
            return;
        }

        symbols.put(token.getText(), type);
    }

    public Type getSymbol(Token token) {
        if (!symbols.containsKey(token.getText())) {
            Errors.addError(token, "Symbol " + token.getText() + " not declared.");
            return Type.ERROR;
        }

        return symbols.get(token.getText());
    }
}
