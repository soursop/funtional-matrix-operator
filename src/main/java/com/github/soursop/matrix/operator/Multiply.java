package com.github.soursop.matrix.operator;

public class Multiply extends AbstractOperators {
    protected Multiply(Operator... operators) {
        super(Sign.MULTIPLY, operators);
    }
}