package com.example.compilTest.core.scanner.finiteautomaton;

import com.example.compilTest.core.scanner.finiteautomaton.perl.PerlAutomatonSettings;
import com.example.compilTest.core.utils.FileLoader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 */
public class FiniteAutomaton {

    private int currentState;
    private List<AcceptState> acceptStatesList;
    private int startState;
    private List<Transition> transitionsList;
    private List<Integer> backTransitions;
    private String commentSymbol;
    private Set<Integer> stringStates;

    public FiniteAutomaton() {
        acceptStatesList = new LinkedList<AcceptState>();
        transitionsList = new LinkedList<Transition>();
        backTransitions = new LinkedList<Integer>();
    }

    public static void main(String[] args) throws IOException {
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton();
        finiteAutomaton.setStartState(PerlAutomatonSettings.getStartState());
        finiteAutomaton.setAcceptStatesList(PerlAutomatonSettings.getAcceptStatesList());
        finiteAutomaton.setTransitionsList(PerlAutomatonSettings.getTransitions());
        finiteAutomaton.setBackTransitions(PerlAutomatonSettings.getBackTransitions());
        finiteAutomaton.setCommentSymbol(PerlAutomatonSettings.getCommentSymbol());
        finiteAutomaton.setStringStates(PerlAutomatonSettings.getStringStates());
        List<Token> tokensList = finiteAutomaton.getScannedTokens(FileLoader.readTheFile("C:\\Users\\Mark\\Desktop\\perl.txt"));
        for (Token t : tokensList) {
            System.out.println(t.getLexicalUnit() + "   " + t.getReturnValue());
        }

    }

    public void setAcceptStatesList(List<AcceptState> acceptStatesList) {
        this.acceptStatesList = acceptStatesList;
    }

    public void setTransitionsList(List<Transition> transitionsList) {
        this.transitionsList.clear();
        this.transitionsList.addAll(transitionsList);
    }

    public void setBackTransitions(List<Integer> backTransitions) {
        this.backTransitions = backTransitions;
    }

    public void setStartState(int startState) {
        this.startState = startState;
        currentState = startState;
    }

    public void setCommentSymbol(String commentSymbol) {
        this.commentSymbol = commentSymbol;
    }

    public void setStringStates(Set<Integer> stringStates) {
        this.stringStates = stringStates;
    }

    private boolean hasNextState(int numOfState) {
        for (Transition t : transitionsList) {
            if (t.getFromState() == numOfState) {
                return true;
            }
        }
        return false;
    }

    public List<Token> getScannedTokens(String stringToScan) {
        List<Token> tokensList = new LinkedList<Token>();
        StringBuilder currentString = new StringBuilder();
        Scanner scanner = new Scanner(stringToScan);
        boolean isBackTransition = false;
        while (scanner.hasNextLine()) {
            if(stringStates.contains(currentState)){
                currentString.append("\n");
            }
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                do {
                    String character = line.substring(i, i + 1);
                    if (character.equals(commentSymbol) && !stringStates.contains(currentState)) {
                        i = line.length();
                        break;
                    }
                    currentString.append(character);
                    for (Transition t : transitionsList) {
                        if (t.getFromState() == currentState && Pattern.matches(t.getRegularExpression(), character)) {
                            isBackTransition = backTransitions.contains(transitionsList.indexOf(t));
                            if (character.equals(" ")) {
                                isBackTransition = false;
                            }
                            currentState = t.getToState();
                            for (AcceptState acceptState : acceptStatesList) {
                                if (acceptState.getStateNum() == currentState) {
                                    if (acceptState.hasReturnValue()) {
                                        if (acceptState.getTokenReturnType() == LexicalUnit.STRING) {
                                            if (currentString.subSequence(0, 1).equals("q")) {
                                                currentString = new StringBuilder(currentString.toString().replaceFirst("q^", ""));
                                            } else if (currentString.subSequence(0, 1).equals("'")) {
                                                currentString = new StringBuilder(currentString.toString().replaceFirst("'", ""));
                                            } else {
                                                currentString = new StringBuilder(currentString.toString().replaceFirst("\"", ""));
                                            }
                                        }
                                        tokensList.add(new Token(acceptState.getTokenReturnType(), currentString.toString().substring(0, currentString.length() - 1)));
                                    } else {
                                        tokensList.add(new Token(acceptState.getTokenReturnType(), null));
                                    }
                                    currentString.delete(0, currentString.length());
                                    break;
                                }
                            }
                            if (!hasNextState(currentState)) {
                                currentState = startState;
                            }
                            break;
                        }
                    }
                } while (isBackTransition);
            }
        }
        for (int backTransition : backTransitions) {
            if (transitionsList.get(backTransition).getFromState() == currentState) {
                int toState = transitionsList.get(backTransition).getToState();
                for (AcceptState acceptState : acceptStatesList) {
                    if (acceptState.getStateNum() == toState) {
                        if (acceptState.hasReturnValue()) {
                            tokensList.add(new Token(acceptState.getTokenReturnType(), currentString.toString()));
                        } else {
                            tokensList.add(new Token(acceptState.getTokenReturnType(), null));
                        }
                    }
                }
            }
        }
        tokensList.add(new Token(LexicalUnit.getStackBottomSymbol(), null));
        return tokensList;
    }


}
