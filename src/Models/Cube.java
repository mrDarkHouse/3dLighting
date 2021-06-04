package Models;

import ThirdDimension.IModel;
import ThirdDimension.Polyline3D;
import Math.Vector3;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Cube implements IModel {
    private Vector3 LTF, RBN;
    private Color color;

    public Cube(Vector3 LTF, Vector3 RBN, Color color) {
        this.LTF = LTF;
        this.RBN = RBN;
        this.color = color;
    }

    @Override
    public List<Polyline3D> getLines() {
        List<Polyline3D> lines = new LinkedList<>();

        lines.add(new Polyline3D(Arrays.asList(
                new Vertex(new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()), new Vector3(0, -1, 0)),
                new Vertex(new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()), new Vector3(0, -1, 0)),
                new Vertex(new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()), new Vector3(0, -1, 0)),
                new Vertex(new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()), new Vector3(0, -1, 0))
        ), /*Color.YELLOW*/color, true));
        lines.add(new Polyline3D(Arrays.asList(
                new Vertex(new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()), new Vector3(0, 1, 0)),
                new Vertex(new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()), new Vector3(0, 1, 0)),
                new Vertex(new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()), new Vector3(0, 1, 0)),
                new Vertex(new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()), new Vector3(0, 1, 0))
        ), /*Color.BLUE*/color, true));

        lines.add(new Polyline3D(Arrays.asList(
                new Vertex(new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()), new Vector3(-1, 0, 0)),
                new Vertex(new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()), new Vector3(-1, 0, 0)),
                new Vertex(new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()), new Vector3(-1, 0, 0)),
                new Vertex(new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()), new Vector3(-1, 0, 0))
        ), /*Color.GREEN*/color, true));

        lines.add(new Polyline3D(Arrays.asList(
                new Vertex(new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()), new Vector3(1, 0, 0)),
                new Vertex(new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()), new Vector3(1, 0, 0)),
                new Vertex(new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()), new Vector3(1, 0, 0)),
                new Vertex(new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()), new Vector3(1, 0, 0))
        ), /*Color.MAGENTA*/color, true));

        lines.add(new Polyline3D(Arrays.asList(
                new Vertex(new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()), new Vector3(0, 0, 1)),
                new Vertex(new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()), new Vector3(0, 0, 1)),
                new Vertex(new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()), new Vector3(0, 0, 1)),
                new Vertex(new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()), new Vector3(0, 0, 1))
        ), /*Color.RED*/color, true));

        lines.add(new Polyline3D(Arrays.asList(
                new Vertex(new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()), new Vector3(0, 0, -1)),
                new Vertex(new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()), new Vector3(0, 0, -1)),
                new Vertex(new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()), new Vector3(0, 0, -1)),
                new Vertex(new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()), new Vector3(0, 0, -1))
        ),/* Color.PINK*/color, true));

        return lines;
    }
}
