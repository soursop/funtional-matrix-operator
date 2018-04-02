package com.github.soursop.matrix.operator;

class DoubleIterator extends DoubleMatrix {
    private final int height;
    private final int width;
    private final double from;

    DoubleIterator(double from, int height, int width) {
        this.from = from;
        this.height = height;
        this.width = width;
    }
    @Override
    public DoubleMatrix head() {
        return None.DOUBLE_MATRIX;
    }

    @Override
    public DoubleMatrix tail() {
        return None.DOUBLE_MATRIX;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public double valueOf(int height, int width) {
        Assert.assertIndexException(height, width, this);
        return from;
    }

    @Override
    public Matrix transpose() {
        return new DoubleMatrixTranspose<>(this);
    }
}
