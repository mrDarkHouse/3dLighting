package Math;

public class Vector3 {

    private double[] coords;

    public Vector3(double x, double y, double z){
        coords = new double[]{x,y,z};
    }

    public Vector3(Vector4 v){
        if (Math.abs(v.getW()) < 1e-12)
            coords = new double[]{ v.getX(), v.getY(), v.getZ()};
        else
            coords = new double[]{
                    v.getX() / v.getW(),
                    v.getY() / v.getW(),
                    v.getZ() / v.getW(),
            };
    }

    public double getX(){
        return coords[0];
    }

    public double getY(){
        return coords[1];
    }

    public double getZ(){
        return coords[2];
    }

    public Vector3 normalize(){
        double d = Math.sqrt(coords[0]*coords[0] + coords[1]*coords[1] + coords[2]*coords[2]);
        return new Vector3(coords[0]/d, coords[1]/d, coords[2]/d);
    }
    public Vector3 substract(Vector3 other){
        return new Vector3(getX() - other.getX(), getY() - other.getY(), getZ() - other.getZ());
    }
    public double dotProduct(Vector3 other){
        return getX()*other.getX() + getY()*other.getY() + getZ()*other.getZ();
    }

    public double at(int index){
        return coords[index];
    }

    public static Vector3 zero(){
        return new Vector3(0, 0, 0);
    }

    @Override
    public String toString() {
        return "[" + coords[0] + " " + coords[1] + " " + coords[2] + "]";
    }
}
