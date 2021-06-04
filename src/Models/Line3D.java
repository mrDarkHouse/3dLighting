package Models;

import Math.Vector3;
import ThirdDimension.IModel;
import ThirdDimension.Polyline3D;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Line3D implements IModel {
    private Vector3 p1, p2;
    Color color;
    public Line3D(Vector3 p1, Vector3 p2, Color color){
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }

    @Override
    public List<Polyline3D> getLines() {
        Polyline3D pl = new Polyline3D(Arrays.asList(new Vertex(p1), new Vertex(p2)), color,false);
        return Collections.singletonList(pl);
    }
}
