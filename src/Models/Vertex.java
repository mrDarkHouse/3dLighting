package Models;

import Math.Vector3;

public class Vertex {
    private Vector3 position;
    private Vector3 normal;

    public Vector3 getPosition() {
        return position;
    }
    public Vector3 getNormal() {
        return normal;
    }
    public void setNormal(Vector3 normal) {
        this.normal = normal;
    }

    public Vertex(Vector3 position) {
        this.position = position;
    }

    public Vertex(Vector3 position, Vector3 normal) {
        this(position);
        setNormal(normal);
    }

    @Override
    public String toString() {
        return "[pos: " + position + "| norm: " + normal + "]";
    }
}
