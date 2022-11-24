package me.darwj1.crafteffect3d.math.geometry.shapes;

import me.darwj1.crafteffect3d.math.geometry.Triangle;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Abstraction of a shape.
 */
public interface Shape {

    public List<Triangle> surfaceTriangles();
    public List<Vector> surfacePoints(float precision);

    public void xRotateAroundPoint(Vector point, float angleRad);
    public void yRotateAroundPoint(Vector point, float angleRad);
    public void zRotateAroundPoint(Vector point, float angleRad);

    public void xyzScaleAroundPoint(Vector point, float x, float y, float z);

}
