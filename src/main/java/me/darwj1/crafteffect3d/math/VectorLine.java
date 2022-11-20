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

    // TODO: compute & handle distances from center
    public void rotateX(float yawAngleRad) {
        VectorMatrix.transform(P1,
                VectorMatrix.xRotationMatrix(yawAngleRad));
        VectorMatrix.transform(P2,
                VectorMatrix.xRotationMatrix(yawAngleRad));
        line = P2.subtract(P1);
    }
    public void rotateY(float pitchAngleRad) {
        VectorMatrix.transform(P1,
                VectorMatrix.yRotationMatrix(pitchAngleRad));
        VectorMatrix.transform(P2,
                VectorMatrix.yRotationMatrix(pitchAngleRad));
        line = P2.subtract(P1);
    }
    public void rotateZ(float rollAngleRad) {
        VectorMatrix.transform(P1,
                VectorMatrix.zRotationMatrix(rollAngleRad));
        VectorMatrix.transform(P2,
                VectorMatrix.zRotationMatrix(rollAngleRad));
        line = P2.subtract(P1);
    }

}
