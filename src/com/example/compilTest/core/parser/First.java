package com.example.compilTest.core.parser;

import com.example.compilTest.core.parser.perl.PerlGrammar;
import com.example.compilTest.core.scanner.finiteautomaton.LexicalUnit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 */
public class First {

    private Nonterminal nonterminal;
    private List<LexicalUnit> firstList;

    public First(Nonterminal nonterminal, List<LexicalUnit> firstList) {
        this.nonterminal = nonterminal;
        this.firstList = firstList;
    }

    public static List<First> getAllFirstList() {
        List<First> allFirstList = new LinkedList<First>();
        for (Nonterminal nonterminal : Nonterminal.values()) {
            allFirstList.add(getFirst(nonterminal));
        }
        return allFirstList;
    }

    public static First getFirst(Nonterminal nonterminal) {
        Set<LexicalUnit> first = new HashSet<LexicalUnit>();
        for (GrammarRule rule : PerlGrammar.getGrammarRules()) {
            if (rule.getLeftSideNonterminal() == nonterminal) {
                first.addAll(getFirst(rule.getRightSideRule()));
            }
        }
        return new First(nonterminal, new LinkedList<LexicalUnit>(first));
    }

    public static Set<LexicalUnit> getFirst(List<Object> rule){
        Set<LexicalUnit> first = new HashSet<LexicalUnit>();
            if (rule.get(0) != null && rule.get(0).getClass() == LexicalUnit.class) {
                first.add((LexicalUnit) rule.get(0));
            } else if (rule.get(0) == null) {
                first.add(null);
            } else {
                for (Object unit : rule) {
                    if (unit.getClass() == Nonterminal.class) {
                        First f = getFirst((Nonterminal) unit);
                        first.addAll(f.getFirstList());
                        if(!f.getFirstList().contains(null)){
                            break;
                        }
                    } else if (unit.getClass() == LexicalUnit.class){
                        first.add((LexicalUnit) unit);
                        break;
                    }
            }
        }
        return first;
    }

    public static void main(String[] args) {
        for (First f : getAllFirstList()) {
            System.out.println(f.getNonterminal().toString());
            for (LexicalUnit unit : f.getFirstList()) {
                if (unit != null) System.out.print(unit.toString() + " ");
                else System.out.println("NULL ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public Nonterminal getNonterminal() {
        return nonterminal;
    }

    public List<LexicalUnit> getFirstList() {
        return firstList;
    }

}
