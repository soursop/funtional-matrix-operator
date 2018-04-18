package com.github.soursop.matrix.operator.ml;

import com.github.soursop.matrix.operator.*;

/**
 * @author soursop
 * @created 2018. 4. 18.
 */
public class Hypothesis {
    private static int DERIVATIVE_OF_POW = 2;
    private final Transposable input;
    private final Transposable output;
    private final Gradient gradient = new Gradient(None.DOUBLE_MATRIX);

    public Hypothesis(Transposable input, Transposable output) {
        this.input = input;
        this.output = output;
    }

    private Multiply hypothesis(DoubleMatrix theta) {
        return input.multiply(theta);
    }

    public Plus cost(DoubleMatrix theta) {
        return hypothesis(theta).minus(output);
    }

    public double error(DoubleMatrix theta) {
        Plus cost = cost(theta);
        return cost.pow(DERIVATIVE_OF_POW).avg().getValue() / DERIVATIVE_OF_POW;
    }

    public Gradient gradient(DoubleMatrix theta) {
        gradient.theta = theta;
        return gradient;
    }

    public Prepare repeat(int repeat) {
        return new Prepare(repeat);
    }

    public class Prepare {
        private final int repeat;
        private Prepare(int repeat) {
            this.repeat = repeat;
        }

        public Repeat theta(DoubleMatrix theta) {
            return new Repeat(theta);
        }

        public class Repeat {
            private DoubleMatrix theta;
            Repeat(DoubleMatrix theta) {
                this.theta = theta;
            }

            public DoubleMatrix decent(DoubleOperator ratio) {
                for (int i = 0; i < repeat; i++) {
                    theta = gradient(theta).decent(ratio);
                }
                return theta;
            }
        }
    }

    public class Gradient {
        private DoubleMatrix theta;
        private Gradient(DoubleMatrix theta) {
            this.theta = theta;
        }

        public DoubleMatrix decent(DoubleOperator ratio) {
            Plus cost = cost(theta);
            Multiply gradient = cost.multiply(ratio);
            Operators decent = input.transpose().multiply(gradient);
            return theta.minus(decent).invoke();
        }
    }
}
