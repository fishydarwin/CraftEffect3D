package me.darwj1.crafteffect3d.math;

/**
 * 3x3 Square Matrix implementation.
 */
public class Matrix3f {

    private Float[] matrixArray =
            {0f, 0f, 0f,
             0f, 0f, 0f,
             0f, 0f, 0f};

    public Matrix3f() {}
    public Matrix3f(Float[] initialMatrixArray) {
        matrixArray = initialMatrixArray;
    }

    public static final Matrix3f IDENTITY = new Matrix3f(
            new Float[]
            {1f, 0f, 0f,
             0f, 1f, 0f,
             0f, 0f, 1f});

    public Float[] value() { return matrixArray; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix3f)) { return false; }
        Matrix3f m = (Matrix3f) o;
        for (int i = 0; i < 9; i++) {
            if (!m.value()[i].equals(matrixArray[i])) { return false; }
        }
        return true;
    }

    public void add(Matrix3f matrix) {
        for (int i = 0; i < 9; i++) {
            matrixArray[i] += matrix.value()[i];
        }
    }

    public void mul(Float scalar) {
        for (int i = 0; i < 9; i++) {
            matrixArray[i] *= scalar;
        }
    }

    public void mul(Matrix3f matrix) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                matrixArray[3 * row + col] =
                        matrixArray[3 * row] * matrix.value()[col] +
                                matrixArray[3 * row + 1] * matrix.value()[3 + col] +
                                matrixArray[3 * row + 2] * matrix.value()[6 + col];
            }
        }
    }

}
