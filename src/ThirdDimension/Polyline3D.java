package ThirdDimension;
import Math.Vector3;
import Models.Vertex;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polyline3D {
    private List<Vertex> points;
    private boolean closed;
    private Color color;
    private Vector3 normal;

    private ArrayList<Color> modifiedColors;

    public ArrayList<Color> getModifiedColors() {
        return modifiedColors;
    }

    public Polyline3D setModifiedColors(ArrayList<Color> modifiedColors) {
        this.modifiedColors = modifiedColors;
        return this;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public Polyline3D(List<Vertex> points, Color color, boolean closed) {
        this.points = new ArrayList<>(points);
        this.closed = closed;
        this.color = color;
    }

    public Polyline3D setNormal(Vector3 normal) {
        this.normal = normal;
        return this;
    }

    public List<Vertex> getPoints() {
        return points;
    }

    public boolean isClosed() {
        return closed;
    }

    public double getAvgZ(){
        double z = 0;
        for(Vertex p:points){
            z += p.getPosition().getZ();
        }
        z = z/points.size();
        return z;
    }

    public Vector3 normal(){
        if(normal == null) return calcNormal();
        else return normal;
    }
    public Vector3 calcNormal(){
        double a =
                points.get(0).getPosition().getY()*(points.get(1).getPosition().getZ() - points.get(2).getPosition().getZ()) +
                points.get(1).getPosition().getY()*(points.get(2).getPosition().getZ() - points.get(0).getPosition().getZ()) +
                points.get(2).getPosition().getY()*(points.get(0).getPosition().getZ() - points.get(1).getPosition().getZ());
        double b =
                points.get(0).getPosition().getZ()*(points.get(1).getPosition().getX() - points.get(2).getPosition().getX()) +
                points.get(1).getPosition().getZ()*(points.get(2).getPosition().getX() - points.get(0).getPosition().getX()) +
                points.get(2).getPosition().getZ()*(points.get(0).getPosition().getX() - points.get(1).getPosition().getX());
        double c =
                points.get(0).getPosition().getX()*(points.get(1).getPosition().getY() - points.get(2).getPosition().getY()) +
                points.get(1).getPosition().getX()*(points.get(2).getPosition().getY() - points.get(0).getPosition().getY()) +
                points.get(2).getPosition().getX()*(points.get(0).getPosition().getY() - points.get(1).getPosition().getY());
        return new Vector3(a, b, c).normalize();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < points.size(); i++) {
            s.append(points.toString());
            s.append("\n");
        }
        return s.toString();
    }
}
