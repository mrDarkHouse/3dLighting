package ThirdDimension;
import Math.Matrix4;
import Math.Vector3;
import Math.Vector4;

public class Camera {
    public Matrix4 scale, rotate, translate, projection;

    public Camera() {
        scale = Matrix4.one();
        rotate = Matrix4.one();
        translate = Matrix4.one();
        projection = Matrix4.one();
    }

    public Vector3 w2c(Vector3 v){
        return new Vector3(
            projection.mul(
                    translate.mul(
                            rotate.mul(
                                    scale.mul(
                                            new Vector4(v)
                                    )
                            )
                    )
            )
        );
    }
}
