package com.github.soursop.matrix.operator;

import java.util.List;

public interface DoubleMatrix extends Matrix, Transposable<DoubleMatrix> {
    double valueOf(int height, int width);
    double valueOf(int idx);
    double[] row(int row);
    double[] values();
    int[] pos();
    DoubleMatrix transpose();
    List<DoubleMatrix> splitBy(int size);
    DoubleMatrix head();
    DoubleMatrix tail();
    DoubleMatrix last();
    DoubleMatrix init();
    DoubleMatrix copy();
}
