package com.example.compilTest.core.scanner.finiteautomaton;

/**
 * Created with IntelliJ IDEA.
 */
public class AcceptState {

    private int stateNum;
    private LexicalUnit tokenReturnType;
    private boolean hasReturnValue;

    public AcceptState(int stateNum, LexicalUnit tokenReturnType, boolean hasReturnValue) {
        this.stateNum = stateNum;
        this.tokenReturnType = tokenReturnType;
        this.hasReturnValue = hasReturnValue;
    }

    public int getStateNum() {
        return stateNum;
    }

    public LexicalUnit getTokenReturnType() {
        return tokenReturnType;
    }

    public boolean hasReturnValue() {
        return hasReturnValue;
    }

}
