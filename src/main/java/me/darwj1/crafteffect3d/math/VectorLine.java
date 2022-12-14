package me.darwj1.crafteffect3d.math;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A line between two points, with helpful functions.
 */
public class VectorLine {

    private Vector P1;
    private Vector P2;
    private Vector line;
    private double lineLength;

    public VectorLine(@NotNull Location L1, @NotNull Location L2) {
        P1 = L1.toVector();
        P2 = L2.toVector();
        line = P2.clone().subtract(P1);
        lineLength = line.length();
    }
    public VectorLine(Vector V1, Vector V2) {
        P1 = V1;
        P2 = V2;
        line = P2.clone().subtract(P1);
        lineLength = line.length();
    }

    public final double length() {
        return lineLength;
    }

    public List<Vector> pointsBetween(float precision) {
        List<Vector> points = new ArrayList<>();

        Vector segment = line.clone().normalize().multiply(precision);
        Vector currentSegment = P1.clone();

        for (float i = 0; i <= lineLength; i += precision) {
            points.add(currentSegment.clone());
            currentSegment.add(segment);
        }

        return points;
    }

    public void xRotateAroundPoint(Vector point, float angleRad) {
        VectorMatrix.xRotateAroundPoint(P1, point, angleRad);
        VectorMatrix.xRotateAroundPoint(P2, point, angleRad);
        line = P2.subtract(P1);
    }
    public void yRotateAroundPoint(Vector point, float angleRad) {
        VectorMatrix.yRotateAroundPoint(P1, point, angleRad);
        VectorMatrix.yRotateAroundPoint(P2, point, angleRad);
        line = P2.subtract(P1);
    }
    public void zRotateAroundPoint(Vector point, float angleRad) {
        VectorMatrix.zRotateAroundPoint(P1, point, angleRad);
        VectorMatrix.zRotateAroundPoint(P2, point, angleRad);
        line = P2.subtract(P1);
    }

    public void xyzScaleAroundPoint(Vector point, float x, float y, float z) {
        VectorMatrix.xyzScaleAroundPoint(P1, point, x, y, z);
        VectorMatrix.xyzScaleAroundPoint(P2, point, x, y, z);
        line = P2.subtract(P1);
    }

}
