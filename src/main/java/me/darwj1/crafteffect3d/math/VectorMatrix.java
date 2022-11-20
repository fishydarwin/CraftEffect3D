package me.darwj1.crafteffect3d.math;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Utilities for operating on vectors using matrices.
 */
public class VectorMatrix {

    public static void transform(@NotNull Vector vector, @NotNull Matrix3f matrix) {
        Matrix3f matrix_v = new Matrix3f(new Float[] {
                (float) vector.getX(), 0f, 0f,
                (float) vector.getY(), 0f, 0f,
                (float) vector.getZ(), 0f, 0f
        });
        matrix.mul(matrix_v);

        vector.setX(matrix.value()[0]);
        vector.setY(matrix.value()[3]);
        vector.setZ(matrix.value()[6]);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3f xRotationMatrix(float yawAngleRad) {
        return new Matrix3f(new Float[] {
                1f, 0f, 0f,
                0f, (float) Math.cos(yawAngleRad), (float) -Math.sin(yawAngleRad),
                0f, (float) Math.sin(yawAngleRad), (float) Math.cos(yawAngleRad)
        });
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3f yRotationMatrix(float pitchAngleRad) {
        return new Matrix3f(new Float[] {
                (float) Math.cos(pitchAngleRad), 0f, (float) -Math.sin(pitchAngleRad),
                0f, 1f, 0f,
                (float) Math.sin(pitchAngleRad), 0f, (float) Math.cos(pitchAngleRad)
        });
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3f zRotationMatrix(float rollAngleRad) {
        return new Matrix3f(new Float[] {
                (float) Math.cos(rollAngleRad), (float) -Math.sin(rollAngleRad), 0f,
                (float) Math.sin(rollAngleRad), (float) Math.cos(rollAngleRad), 0f,
                0f, 0f, 1f
        });
    }

}
