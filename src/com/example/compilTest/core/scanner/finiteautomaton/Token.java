package com.example.compilTest.core.scanner.finiteautomaton;

/**
 * Created with IntelliJ IDEA.
 */
public class Token {

    private LexicalUnit lexicalUnit;
    private String returnValue;

    public Token(LexicalUnit lexicalUnit, String returnValue) {
        this.lexicalUnit = lexicalUnit;
        this.returnValue = returnValue;
    }

    public LexicalUnit getLexicalUnit() {
        return lexicalUnit;
    }

    public String getReturnValue() {
        return returnValue;
    }
}
