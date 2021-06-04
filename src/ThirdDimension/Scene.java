package ThirdDimension;
import Light.LightSource;
import Math.*;
import Models.Vertex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Scene {
    public List<IModel> models = new LinkedList<>();
    private List<LightSource> sources = new LinkedList<>();
    private float globalLight = 0.0f;

    public List<LightSource> getSources() {
        return sources;
    }
    public void addLightSource(LightSource ls){
        sources.add(ls);
    }

    public float getGlobalLight() {
        return globalLight;
    }
    public void setGlobalLight(float globalLight) {
        this.globalLight = globalLight;
    }

    private IModel selectedModel;
    public IModel getSelectedModel() {
        return selectedModel;
    }
    public void setSelectedModel(IModel selectedModel) {
        this.selectedModel = selectedModel;
    }

    public void changeColorOfModel(Color c){
        for (int i = 0; i < selectedModel.getLines().size(); i++) {
            Polyline3D l = selectedModel.getLines().get(i);
            l.setColor(c);
        }
    }

    public BufferedImage drawScene(Camera cam, ScreenConverter sc){
        BufferedImage bi = new BufferedImage(sc.getWs(), sc.getHs(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)bi.getGraphics();
        /**/
        List<Polyline3D> lines = new ArrayList<>();
        for(IModel m:models){
            for (Polyline3D pl:m.getLines()){
                List<Vertex> points = new LinkedList<>();
                for (Vertex v:pl.getPoints()){
                    points.add(new Vertex(cam.w2c(v.getPosition()), v.getNormal()));
                }
                Polyline3D tmp = new Polyline3D(points, getPolygonColor(pl), pl.isClosed());
                lines.add(tmp);
            }
        }

        lines.sort((o1, o2) -> (int)Math.signum(o1.getAvgZ() - o2.getAvgZ()));
        g.setColor(Color.WHITE);
        g.fillRect(0,0, bi.getWidth(), bi.getHeight());

        g.setColor(Color.BLACK);
        for(Polyline3D pl:lines){
            int[] xx = new int[pl.getPoints().size()];
            int[] yy = new int[pl.getPoints().size()];
            int i = 0;
            for (Vertex v:pl.getPoints()){
                ScreenPoint sp = sc.r2s(v.getPosition());
                xx[i] = sp.getI();
                yy[i] = sp.getJ();
                i++;
            }
            if(pl.isClosed()) {
                g.setColor(pl.getColor());
                g.fillPolygon(xx, yy, xx.length);
            }
            g.setColor(Color.black);
            for (int j = 1; j < xx.length; j++) {
                g.drawLine(xx[j - 1], yy[j - 1], xx[j], yy[j]);
            }
            if (pl.isClosed())
                g.drawLine(xx[0], yy[0], xx[xx.length - 1], yy[yy.length - 1]);
        }
        for (LightSource ls:sources){
            ScreenPoint p = sc.r2s(cam.w2c(ls.getPosition()));
            g.setColor(ls.getColor());
            g.fillOval(p.getI(), p.getJ(), 10, 10);
            g.setColor(Color.BLACK);
            g.drawOval(p.getI(), p.getJ(), 10, 10);
        }

        g.dispose();
        return bi;
    }

    private void drawPolygon(Graphics2D g2d, ScreenConverter sc, Polyline3D pl) {
        ScreenPoint[] pt = new ScreenPoint[4];
        int[] xx = new int[4];
        int[] yy = new int[4];
        for (int i = 0; i < pl.getPoints().size(); i++) {
            pt[i] = sc.r2s(pl.getPoints().get(i).getPosition());
            xx[i] = pt[i].getI();
            yy[i] = pt[i].getJ();
        }
    }

    private Color getPolygonColor(Polyline3D polyline){
        Vector3 normal = polyline.normal();
        LightSource ls1 = sources.get(0);
        LightSource ls2 = sources.get(1);

        Vector3 toLight1 = /*ls1 == null ? Vector3.zero() :*/
                ls1.getPosition().substract(polyline.getPoints().get(0).getPosition()).normalize();
        Vector3 toLight2 = /*ls2 == null ? Vector3.zero() :*/
                ls2.getPosition().substract(polyline.getPoints().get(0).getPosition()).normalize();

        double d1 = Math.max(0, normal.dotProduct(toLight1));
        double d2 = Math.max(0, normal.dotProduct(toLight2));

//        double d = Math.min(d1 + d2 + globalLight, 1);

        Color c1 = /*ls1 == null ? Color.BLACK : */ls1.getColor();
        Color c2 = /*ls2 == null ? Color.BLACK : */ls2.getColor();
        if(!ls1.isWork()) d1 = 0;
        if(!ls2.isWork()) d2 = 0;

        Color old = polyline.getColor();
        double red = Math.min(255,
                globalLight * old.getRed() +
                d1 * old.getRed() + d1 * c1.getRed() +
                d2 * old.getRed() + d2 * c2.getRed()
        );

        double green = Math.min(255,
                globalLight * old.getGreen() +
                d1 * old.getGreen() + d1 * c1.getGreen() +
                d2 * old.getGreen() + d2 * c2.getGreen()
        );

        double blue = Math.min(255,
                globalLight * old.getBlue() +
                d1 * old.getBlue() + d1 * c1.getBlue() +
                d2 * old.getBlue() + d2 * c2.getBlue()
        );
        return new Color((int)red, (int)green, (int)blue);
    }

    private ArrayList<Color> getPolygonColors(Polyline3D polyline){
        ArrayList<Color> c = new ArrayList<>();
        c.add(Color.RED);
        c.add(Color.YELLOW);
        c.add(Color.GREEN);
        c.add(Color.BLUE);
        return c;
    }
}
