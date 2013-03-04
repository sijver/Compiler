package com.example.compilTest.core.scanner.finiteautomaton;

/**
 * Created with IntelliJ IDEA.
 */
public class Transition {

    private int fromState;
    private int toState;
    private String regularExpression;

    public Transition(int fromState, int toState, String regularExpression) {
        this.fromState = fromState;
        this.toState = toState;
        this.regularExpression = regularExpression;
    }

    public int getFromState() {
        return fromState;
    }

    public void setFromState(int fromState) {
        this.fromState = fromState;
    }

    public int getToState() {
        return toState;
    }

    public void setToState(int toState) {
        this.toState = toState;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

}
