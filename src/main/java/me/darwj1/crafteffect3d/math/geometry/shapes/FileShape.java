package me.darwj1.crafteffect3d.math.geometry.shapes;

import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Abstraction of a shape which gets vertex data from a file.
 */
public interface FileShape extends Shape {

    List<Vector> readVertices(File file) throws IOException;

}
