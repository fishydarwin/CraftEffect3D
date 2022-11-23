package me.darwj1.crafteffect3d.math.geometry.shapes;

import me.darwj1.crafteffect3d.math.VectorMatrix;
import me.darwj1.crafteffect3d.math.geometry.Triangle;
import org.bukkit.util.Vector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Shape implementation using .obj files.
 */
public class ObjShape implements FileShape {

    private List<Vector> vertices;
    private List<List<Integer>> faces;

    public ObjShape(File file) throws IOException {
        vertices = new ArrayList<>();
        readVertices(file);
    }

    @Override
    public List<Triangle> surfaceTriangles() {
        List<Triangle> triangles = new ArrayList<>();

        for (List<Integer> face : faces) {
            Vector vertex1 = vertices.get(face.get(0));
            Vector vertex2 = vertices.get(face.get(1));
            Vector vertex3 = vertices.get(face.get(2));
            triangles.add(new Triangle(vertex1, vertex2, vertex3));
        }

        return triangles;
    }

    @Override
    public void xRotateAroundPoint(Vector point, float angleRad) {
        for (Vector vertex : vertices) {
            VectorMatrix.xRotateAroundPoint(vertex, point, angleRad);
        }
    }

    @Override
    public void yRotateAroundPoint(Vector point, float angleRad) {
        for (Vector vertex : vertices) {
            VectorMatrix.yRotateAroundPoint(vertex, point, angleRad);
        }
    }

    @Override
    public void zRotateAroundPoint(Vector point, float angleRad) {
        for (Vector vertex : vertices) {
            VectorMatrix.zRotateAroundPoint(vertex, point, angleRad);
        }
    }

    @Override
    public void xyzScaleAroundPoint(Vector point, float x, float y, float z) {
        for (Vector vertex : vertices) {
            VectorMatrix.xyzScaleAroundPoint(vertex, point, x, y, z);
        }
    }

    @Override
    public List<Vector> readVertices(File file) throws IOException {
        vertices = new ArrayList<>();
        faces = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine();
        while (line != null) {
            if (line.startsWith("v ")) {
                String[] split = line.split(" ");
                float x = Float.parseFloat(split[1]);
                float y = Float.parseFloat(split[2]);
                float z = Float.parseFloat(split[3]);
                vertices.add(new Vector(x, y, z));
            } else if (line.startsWith("f ")) {

                line.replace("//", "/");
                String[] split = line.split(" ");
                int v1i = Integer.parseInt(split[1].split(Pattern.quote("/"))[0]);
                int v2i = Integer.parseInt(split[2].split(Pattern.quote("/"))[0]);
                int v3i = Integer.parseInt(split[3].split(Pattern.quote("/"))[0]);

                List<Integer> face = new ArrayList<>();
                face.add(v1i - 1);
                face.add(v2i - 1);
                face.add(v3i - 1);
                faces.add(face);
            }
            line = reader.readLine();
        }

        return vertices;
    }
}
