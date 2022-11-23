package me.darwj1.crafteffect3d.math.geometry;

import me.darwj1.crafteffect3d.math.VectorLine;
import me.darwj1.crafteffect3d.math.VectorMatrix;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

    private Vector point1;
    private Vector point2;
    private Vector point3;

    public Triangle(Vector point1, Vector point2, Vector point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        recalculate();
    }

    private VectorLine vl12;
    private VectorLine vl13;
    private void recalculate() {
        vl12 = new VectorLine(point1, point2);
        vl13 = new VectorLine(point1, point3);
    }

    public List<Vector> surfacePoints(float precision) {
        List<Vector> points = new ArrayList<>();

        float newPrecision;
        int pointsSize;

        List<Vector> vl12Points;
        List<Vector> vl13Points;
        if (vl12.length() > vl13.length()) {
            vl12Points = vl12.pointsBetween(precision);
            pointsSize = vl12Points.size();
            newPrecision = (float) vl13.length() / pointsSize;
            vl13Points = vl13.pointsBetween(newPrecision);
        } else {
            vl13Points = vl13.pointsBetween(precision);
            pointsSize = vl13Points.size();
            newPrecision = (float) vl12.length() / pointsSize;
            vl12Points = vl12.pointsBetween(newPrecision);
        }

        for (int i = 1; i < pointsSize; i++) {
            VectorLine surfaceLine = new VectorLine(vl12Points.get(i), vl13Points.get(i));
            points.addAll(surfaceLine.pointsBetween(precision));
        }

        return points;
    }

    public void xRotateAroundPoint(Vector point, float angleRad) {
        VectorMatrix.xRotateAroundPoint(point1, point, angleRad);
        VectorMatrix.xRotateAroundPoint(point2, point, angleRad);
        VectorMatrix.xRotateAroundPoint(point3, point, angleRad);
        recalculate();
    }
    public void yRotateAroundPoint(Vector point, float angleRad) {
        VectorMatrix.yRotateAroundPoint(point1, point, angleRad);
        VectorMatrix.yRotateAroundPoint(point2, point, angleRad);
        VectorMatrix.yRotateAroundPoint(point3, point, angleRad);
        recalculate();
    }
    public void zRotateAroundPoint(Vector point, float angleRad) {
        VectorMatrix.zRotateAroundPoint(point1, point, angleRad);
        VectorMatrix.zRotateAroundPoint(point2, point, angleRad);
        VectorMatrix.zRotateAroundPoint(point3, point, angleRad);
        recalculate();
    }

}
