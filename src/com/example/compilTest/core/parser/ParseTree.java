package com.example.compilTest.core.parser;

import com.example.compilTest.core.parser.perl.ParsingTable;
import com.example.compilTest.core.parser.perl.PerlGrammar;
import com.example.compilTest.core.scanner.finiteautomaton.FiniteAutomaton;
import com.example.compilTest.core.scanner.finiteautomaton.LexicalUnit;
import com.example.compilTest.core.scanner.finiteautomaton.Token;
import com.example.compilTest.core.scanner.finiteautomaton.perl.PerlAutomatonSettings;
import com.example.compilTest.core.utils.FileLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */
public class ParseTree {

    private List<Token> inputTokensList;
    private ParsingTable parsingTable;
    private List<Integer> outputRulesList;
    private LinkedList<Object> stack;

    public ParseTree(List<Token> inputTokensList) {
        this.inputTokensList = inputTokensList;
        outputRulesList = new LinkedList<Integer>();
        initParsingTable();
        initStack();
    }

    private void initParsingTable() {
        parsingTable = new ParsingTable(Nonterminal.values().length, LexicalUnit.values().length);
        parsingTable.fillTheParsingTable();
    }

    private void initStack() {
        stack = new LinkedList<Object>();
        stack.addFirst(LexicalUnit.getStackBottomSymbol());
        stack.addFirst(Nonterminal.getStartSymbol());
    }

    public void doParsing() {
        boolean isSymbolRead;
        for (Token symbol : inputTokensList) {
            isSymbolRead = false;   // Shows is symbol read and we can work with the next input symbol
            while (!isSymbolRead) {
                if (stack.getFirst().getClass() == LexicalUnit.class) {
                    if (symbol.getLexicalUnit() == stack.getFirst()) {
                        stack.removeFirst();

//                        Debug info
//                        System.out.println("read " + symbol.getLexicalUnit());
//                        System.out.println(Arrays.toString(stack.toArray()));

                        isSymbolRead = true;
                    } else {
                        System.out.println("Parsing error! Top-most symbol of the stack is not equal to the input stream symbol!");
                        System.exit(1);
                    }
                } else {    // Top-most symbol of the stack is the Nonterminal
                    int ruleNumber = parsingTable.getRuleFromCell((Nonterminal) stack.getFirst(), symbol.getLexicalUnit());
                    if (ruleNumber != -1) {
                        outputRulesList.add(ruleNumber);
                        stack.removeFirst();
                        List<Object> addToStack = PerlGrammar.getRuleByNumber(ruleNumber).getRightSideRule();
                        for (int i = addToStack.size() - 1; i >= 0; i--) {
                            if (addToStack.get(i) != null) {
                                stack.addFirst(addToStack.get(i));
                            }
                        }
                    } else {
                        System.out.println("Parsing error! No such rule! " + stack.getFirst() + " " + symbol.getLexicalUnit());
                        System.exit(1);
                    }
                }
            }
        }
    }

    public List<Integer> getOutputRulesList() {
        return outputRulesList;
    }

    public static void main(String[] args) throws IOException {
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton();
        finiteAutomaton.setStartState(PerlAutomatonSettings.getStartState());
        finiteAutomaton.setAcceptStatesList(PerlAutomatonSettings.getAcceptStatesList());
        finiteAutomaton.setTransitionsList(PerlAutomatonSettings.getTransitions());
        finiteAutomaton.setBackTransitions(PerlAutomatonSettings.getBackTransitions());
        finiteAutomaton.setCommentSymbol(PerlAutomatonSettings.getCommentSymbol());
        finiteAutomaton.setStringStates(PerlAutomatonSettings.getStringStates());

        ParseTree parseTree = new ParseTree(finiteAutomaton.getScannedTokens(FileLoader.readTheFile("C:\\Users\\Mark\\Desktop\\perl.txt")));

        parseTree.doParsing();

        System.out.println(Arrays.toString(parseTree.getOutputRulesList().toArray()));
        System.out.println(parseTree.getOutputRulesList().size());
    }
}
