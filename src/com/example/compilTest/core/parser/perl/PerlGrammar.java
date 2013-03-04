package com.example.compilTest.core.parser.perl;

import com.example.compilTest.core.parser.GrammarRule;
import com.example.compilTest.core.parser.Nonterminal;
import com.example.compilTest.core.scanner.finiteautomaton.LexicalUnit;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 */
public class PerlGrammar {

    private static List<GrammarRule> grammarRules;

    private static void createGrammarRules(){
        grammarRules = new LinkedList<GrammarRule>();
        grammarRules.add(new GrammarRule(Nonterminal.PROGRAM, new Object[]{Nonterminal.FUNCTION_LIST, Nonterminal.A}));
        grammarRules.add(new GrammarRule(Nonterminal.PROGRAM, new Object[]{Nonterminal.INSTRUCTION_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.A, new Object[]{Nonterminal.INSTRUCTION_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.A, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.FUNCTION_LIST, new Object[]{Nonterminal.U_FUNCTION_LIST, Nonterminal.V_FUNCTION_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.U_FUNCTION_LIST, new Object[]{Nonterminal.FUNCTION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_FUNCTION_LIST, new Object[]{Nonterminal.FUNCTION, Nonterminal.V_FUNCTION_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.V_FUNCTION_LIST, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.FUNCTION, new Object[]{LexicalUnit.SUB, LexicalUnit.IDENTIFIER, Nonterminal.FUNCTION_ARGUMENT, LexicalUnit.LBRACE, Nonterminal.INSTRUCTION_LIST, LexicalUnit.RBRACE}));
        grammarRules.add(new GrammarRule(Nonterminal.FUNCTION_ARGUMENT, new Object[]{LexicalUnit.LPAR, Nonterminal.ARGUMENT_LIST, LexicalUnit.RPAR}));
        grammarRules.add(new GrammarRule(Nonterminal.FUNCTION_ARGUMENT, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.ARGUMENT_LIST, new Object[]{Nonterminal.U_ARGUMENT_LIST, Nonterminal.V_ARGUMENT_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.U_ARGUMENT_LIST, new Object[]{LexicalUnit.VAR}));
        grammarRules.add(new GrammarRule(Nonterminal.U_ARGUMENT_LIST, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.V_ARGUMENT_LIST, new Object[]{LexicalUnit.COMMA, LexicalUnit.VAR, Nonterminal.V_ARGUMENT_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.V_ARGUMENT_LIST, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.INSTRUCTION_LIST, new Object[]{Nonterminal.U_INSTRUCTION_LIST, Nonterminal.V_INSTRUCTION_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.U_INSTRUCTION_LIST, new Object[]{LexicalUnit.LBRACE, Nonterminal.INSTRUCTION_LIST, LexicalUnit.RBRACE}));
        grammarRules.add(new GrammarRule(Nonterminal.U_INSTRUCTION_LIST, new Object[]{Nonterminal.INSTRUCTION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_INSTRUCTION_LIST, new Object[]{LexicalUnit.SEMICOLON, Nonterminal.INSTRUCTION, Nonterminal.V_INSTRUCTION_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.V_INSTRUCTION_LIST, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.FUNCTION_CALL, new Object[]{LexicalUnit.CALL_MARK, LexicalUnit.IDENTIFIER, Nonterminal.B}));
        grammarRules.add(new GrammarRule(Nonterminal.B, new Object[]{LexicalUnit.LPAR, Nonterminal.ARGUMENT_CALL_LIST, LexicalUnit.RPAR}));
        grammarRules.add(new GrammarRule(Nonterminal.B, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.ARGUMENT_CALL_LIST, new Object[]{Nonterminal.U_ARGUMENT_CALL_LIST, Nonterminal.V_ARGUMENT_CALL_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.U_ARGUMENT_CALL_LIST, new Object[]{Nonterminal.INSTRUCTION}));
        grammarRules.add(new GrammarRule(Nonterminal.U_ARGUMENT_CALL_LIST, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.V_ARGUMENT_CALL_LIST, new Object[]{LexicalUnit.COMMA, Nonterminal.EXPRESSION, Nonterminal.V_ARGUMENT_CALL_LIST}));
        grammarRules.add(new GrammarRule(Nonterminal.V_ARGUMENT_CALL_LIST, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.INSTRUCTION, new Object[]{Nonterminal.CONDITION}));
        grammarRules.add(new GrammarRule(Nonterminal.INSTRUCTION, new Object[]{Nonterminal.EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.INSTRUCTION, new Object[]{LexicalUnit.RETURN, Nonterminal.EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.CONDITION, new Object[]{LexicalUnit.IF, Nonterminal.EXPRESSION, LexicalUnit.LBRACE, Nonterminal.INSTRUCTION_LIST, LexicalUnit.RBRACE, Nonterminal.CONDITION_END}));
        grammarRules.add(new GrammarRule(Nonterminal.CONDITION, new Object[]{LexicalUnit.UNLESS, Nonterminal.EXPRESSION, LexicalUnit.LBRACE, Nonterminal.INSTRUCTION_LIST, LexicalUnit.RBRACE, Nonterminal.CONDITION_END}));
        grammarRules.add(new GrammarRule(Nonterminal.CONDITION_END, new Object[]{LexicalUnit.ELSE, Nonterminal.EXPRESSION, LexicalUnit.LBRACE, Nonterminal.INSTRUCTION_LIST, LexicalUnit.RBRACE, Nonterminal.CONDITION_END}));
        grammarRules.add(new GrammarRule(Nonterminal.CONDITION_END, new Object[]{LexicalUnit.ELSEIF, Nonterminal.EXPRESSION, LexicalUnit.LBRACE, Nonterminal.INSTRUCTION_LIST, LexicalUnit.RBRACE, Nonterminal.CONDITION_END}));
        grammarRules.add(new GrammarRule(Nonterminal.CONDITION_END, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.SIMPLE_EXPRESSION, new Object[]{Nonterminal.FUNCTION_CALL}));
        grammarRules.add(new GrammarRule(Nonterminal.SIMPLE_EXPRESSION, new Object[]{LexicalUnit.VAR}));
        grammarRules.add(new GrammarRule(Nonterminal.SIMPLE_EXPRESSION, new Object[]{LexicalUnit.INTEGER}));
        grammarRules.add(new GrammarRule(Nonterminal.SIMPLE_EXPRESSION, new Object[]{LexicalUnit.STRING}));
        grammarRules.add(new GrammarRule(Nonterminal.EXPRESSION, new Object[]{LexicalUnit.LOW_NOT, Nonterminal.U_P_EXPRESSION, Nonterminal.V_P_EXPRESSION, Nonterminal.O_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.EXPRESSION, new Object[]{Nonterminal.U_P_EXPRESSION, Nonterminal.V_P_EXPRESSION, Nonterminal.O_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.O_EXPRESSION, new Object[]{LexicalUnit.ASSIGN_MARK, Nonterminal.U_P_EXPRESSION, Nonterminal.V_P_EXPRESSION, Nonterminal.O_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.O_EXPRESSION, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.V_P_EXPRESSION, new Object[]{LexicalUnit.LAZY_OR, Nonterminal.U_P_EXPRESSION, Nonterminal.V_P_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_P_EXPRESSION, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.U_P_EXPRESSION, new Object[]{Nonterminal.U_Q_EXPRESSION, Nonterminal.V_Q_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_Q_EXPRESSION, new Object[]{LexicalUnit.LAZY_AND, Nonterminal.U_Q_EXPRESSION, Nonterminal.V_Q_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_Q_EXPRESSION, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.U_Q_EXPRESSION, new Object[]{Nonterminal.U_R_EXPRESSION, Nonterminal.V_R_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_R_EXPRESSION, new Object[]{LexicalUnit.EQUALS, Nonterminal.U_R_EXPRESSION, Nonterminal.V_R_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_R_EXPRESSION, new Object[]{LexicalUnit.DIFFERENT, Nonterminal.U_R_EXPRESSION, Nonterminal.V_R_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_R_EXPRESSION, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.U_R_EXPRESSION, new Object[]{Nonterminal.U_S_EXPRESSION, Nonterminal.V_S_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_S_EXPRESSION, new Object[]{LexicalUnit.LOWER, Nonterminal.U_S_EXPRESSION, Nonterminal.V_S_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_S_EXPRESSION, new Object[]{LexicalUnit.GREATER, Nonterminal.U_S_EXPRESSION, Nonterminal.V_S_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_S_EXPRESSION, new Object[]{LexicalUnit.LOWER_EQUALS, Nonterminal.U_S_EXPRESSION, Nonterminal.V_S_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_S_EXPRESSION, new Object[]{LexicalUnit.GREATER_EQUALS, Nonterminal.U_S_EXPRESSION, Nonterminal.V_S_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_S_EXPRESSION, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.U_S_EXPRESSION, new Object[]{Nonterminal.U_T_EXPRESSION, Nonterminal.V_T_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_T_EXPRESSION, new Object[]{LexicalUnit.PLUS, Nonterminal.U_T_EXPRESSION, Nonterminal.V_T_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_T_EXPRESSION, new Object[]{LexicalUnit.MINUS, Nonterminal.U_T_EXPRESSION, Nonterminal.V_T_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_T_EXPRESSION, new Object[]{LexicalUnit.CONCAT_MARK, Nonterminal.U_T_EXPRESSION, Nonterminal.V_T_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_T_EXPRESSION, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.U_T_EXPRESSION, new Object[]{Nonterminal.U_U_EXPRESSION, Nonterminal.V_U_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_U_EXPRESSION, new Object[]{LexicalUnit.TIMES, Nonterminal.U_U_EXPRESSION, Nonterminal.V_U_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_U_EXPRESSION, new Object[]{LexicalUnit.DIVIDE, Nonterminal.U_U_EXPRESSION, Nonterminal.V_U_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.V_U_EXPRESSION, new Object[]{null}));
        grammarRules.add(new GrammarRule(Nonterminal.U_U_EXPRESSION, new Object[]{LexicalUnit.HIGH_NOT, Nonterminal.X_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.U_U_EXPRESSION, new Object[]{Nonterminal.X_EXPRESSION}));
        grammarRules.add(new GrammarRule(Nonterminal.X_EXPRESSION, new Object[]{LexicalUnit.LPAR, Nonterminal.SIMPLE_EXPRESSION, LexicalUnit.RPAR}));
        grammarRules.add(new GrammarRule(Nonterminal.X_EXPRESSION, new Object[]{Nonterminal.EXPRESSION}));


    }

    public static List<GrammarRule> getGrammarRules() {
        if(grammarRules == null){
            createGrammarRules();
        }
        return grammarRules;
    }

}