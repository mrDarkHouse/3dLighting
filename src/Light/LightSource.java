package Light;

import Math.Vector3;
import java.awt.*;

public class LightSource {
    private Vector3 position;
    private boolean work;

    public Vector3 getPosition() {
        return position;
    }
    public void setPosition(Vector3 position) {
        this.position = position;
    }
    public boolean isWork() {
        return work;
    }
    public void setWork(boolean work) {
        this.work = work;
    }

    private Color color;
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public LightSource(Vector3 position, Color color) {
        this.position = position;
        this.color = color;
    }
}
