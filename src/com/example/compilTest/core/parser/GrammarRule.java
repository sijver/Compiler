package com.example.compilTest.core.parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */
public class GrammarRule {

    private Nonterminal leftSideNonterminal;

    private List<Object> rightSideRule;

    public GrammarRule(Nonterminal leftSideNonterminal, List<Object> rightSideRule) {
        this.leftSideNonterminal = leftSideNonterminal;
        this.rightSideRule = rightSideRule;
    }

    public GrammarRule(Nonterminal leftSideNonterminal, Object[] rightSideRule) {
        this.leftSideNonterminal = leftSideNonterminal;
        this.rightSideRule = new LinkedList<Object>(Arrays.asList(rightSideRule));
    }

    public Nonterminal getLeftSideNonterminal() {
        return leftSideNonterminal;
    }

    public List<Object> getRightSideRule() {
        return rightSideRule;
    }
}
