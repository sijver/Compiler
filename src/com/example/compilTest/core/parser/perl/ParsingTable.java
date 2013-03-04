package com.example.compilTest.core.parser.perl;

import com.example.compilTest.core.parser.First;
import com.example.compilTest.core.parser.Follow;
import com.example.compilTest.core.parser.GrammarRule;
import com.example.compilTest.core.parser.Nonterminal;
import com.example.compilTest.core.scanner.finiteautomaton.LexicalUnit;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */
public class ParsingTable {

    private int[][] parsingTable;

    public ParsingTable(int nonteminalsNum, int lexicalUnitsNum) {
        parsingTable = new int[nonteminalsNum][lexicalUnitsNum];
        for(int i = 0; i <  nonteminalsNum; i++){
            for(int j = 0; j < lexicalUnitsNum; j++){
                parsingTable[i][j] = -1;
            }
        }
    }

    public void setRuleInCell(int nonterminalNum, int lexicalUnitNum, int ruleNum){
        if(parsingTable[nonterminalNum][lexicalUnitNum] != -1){
            System.out.println("FUCK! " + parsingTable[nonterminalNum][lexicalUnitNum] + " " + ruleNum + " in " + Nonterminal.values()[nonterminalNum] + " " + LexicalUnit.values()[lexicalUnitNum]);
        }
        parsingTable[nonterminalNum][lexicalUnitNum] = ruleNum;
    }

    public int getRuleFromCell(int nonterminalNum, int lexicalUnitNum){
        return parsingTable[nonterminalNum][lexicalUnitNum];
    }

    public void fillTheParsingTable(){
        for(int i = 0; i < PerlGrammar.getGrammarRules().size(); i++){
            GrammarRule rule = PerlGrammar.getGrammarRules().get(i);
            List<LexicalUnit> first = new LinkedList<LexicalUnit>(First.getFirst(rule.getRightSideRule()));
            for(LexicalUnit lexicalUnitFirst : first){
                if(lexicalUnitFirst != null){
                    setRuleInCell(rule.getLeftSideNonterminal().ordinal(), lexicalUnitFirst.ordinal(), i);
                } else {
                    List<LexicalUnit> follow = Follow.getFollow(rule.getLeftSideNonterminal()).getFollowList();
                    for(LexicalUnit lexicalUnitFollow : follow){
                        setRuleInCell(rule.getLeftSideNonterminal().ordinal(), lexicalUnitFollow.ordinal(), i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ParsingTable parsingTable = new ParsingTable(Nonterminal.values().length, LexicalUnit.values().length);
        parsingTable.fillTheParsingTable();
        for(int i = 0; i <  Nonterminal.values().length; i++){
            for(int j = 0; j < LexicalUnit.values().length; j++){
                System.out.print(parsingTable.getRuleFromCell(i, j) + " ");
            }
            System.out.println();
        }
    }


}
