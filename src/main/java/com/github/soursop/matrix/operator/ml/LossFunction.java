package com.github.soursop.matrix.operator.ml;

import com.github.soursop.matrix.operator.*;

import static com.github.soursop.matrix.operator.DoubleOperator.ONE;

public interface LossFunction {
    double loss(DoubleMatrix y, DoubleMatrix hypothesis);

    LossFunction DEFAULT = new LossFunction() {
        @Override
        public double loss(DoubleMatrix y, DoubleMatrix hypothesis) {
            Plus error = hypothesis.minus(y);
            return error.pow(2).sum().getValue() / (y.height() * 2);
        }
    }
    ;
    LossFunction LOGISTIC = new LossFunction() {
        @Override
        public double loss(DoubleMatrix y, DoubleMatrix hypothesis) {
            Multiply positive = y.minus().multiply(hypothesis.apply(Function.LOGISTIC));
            Multiply negative = ONE.minus(y).multiply(ONE.minus(hypothesis).apply(Function.LOGISTIC));
            return positive.minus(negative).sum().getValue() / y.height();
        }
    }
    ;
}
