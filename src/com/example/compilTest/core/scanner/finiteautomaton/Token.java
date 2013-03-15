package com.example.compilTest.core.scanner.finiteautomaton;

/**
 * Created with IntelliJ IDEA.
 */
public class Token {

    private LexicalUnit lexicalUnit;
    private String returnValue;
    private int registerUsing = -1;

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

    public int getRegisterUsing() {
        return registerUsing;
    }

    public void setRegisterUsing(int registerUsing) {
        this.registerUsing = registerUsing;
    }

    @Override
    public String toString(){
        return lexicalUnit.toString() + " = " + returnValue;
    }
}
