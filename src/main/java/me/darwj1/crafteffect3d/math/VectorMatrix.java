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
    public static @NotNull Matrix3f xRotationMatrix(float angleRad) {
        return new Matrix3f(new Float[] {
                1f, 0f, 0f,
                0f, (float) Math.cos(angleRad), (float) -Math.sin(angleRad),
                0f, (float) Math.sin(angleRad), (float) Math.cos(angleRad)
        });
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3f yRotationMatrix(float angleRad) {
        return new Matrix3f(new Float[] {
                (float) Math.cos(angleRad), 0f, (float) -Math.sin(angleRad),
                0f, 1f, 0f,
                (float) Math.sin(angleRad), 0f, (float) Math.cos(angleRad)
        });
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3f zRotationMatrix(float angleRad) {
        return new Matrix3f(new Float[] {
                (float) Math.cos(angleRad), (float) -Math.sin(angleRad), 0f,
                (float) Math.sin(angleRad), (float) Math.cos(angleRad), 0f,
                0f, 0f, 1f
        });
    }

    @Contract("_ -> new")
    public static void xRotateAroundPoint(Vector who, Vector point, float angleRad) {
        Vector diff = who.clone().subtract(point);
        transform(diff, xRotationMatrix(angleRad));
        who.copy(diff.add(point));
    }

    @Contract("_ -> new")
    public static void yRotateAroundPoint(Vector who, Vector point, float angleRad) {
        Vector diff = who.clone().subtract(point);
        transform(diff, yRotationMatrix(angleRad));
        who.copy(diff.add(point));
    }

    @Contract("_ -> new")
    public static void zRotateAroundPoint(Vector who, Vector point, float angleRad) {
        Vector diff = who.clone().subtract(point);
        transform(diff, zRotationMatrix(angleRad));
        who.copy(diff.add(point));
    }

}
