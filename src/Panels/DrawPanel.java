package Panels;

import Light.LightSource;
import Math.*;
import ThirdDimension.*;
import Tools.ObjLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DrawPanel extends JComponent implements MouseListener, MouseMotionListener, KeyListener {
    private ScreenConverter sc;
    private Camera cam;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    private static final double CAMERA_SPEED = 0.3;

    public DrawPanel(){
        super();
        sc = new ScreenConverter(-10,20,20,20,Main.DRAW_WIDTH, Main.DRAW_HEIGHT);
        cam = new Camera();
        scene = new Scene();



        LightSource lightSource1 = new LightSource(new Vector3(2, 6, 3), Color.RED);
        lightSource1.setWork(Main.LIGHT1_WORK_ON_START);
        scene.addLightSource(lightSource1);
        LightSource lightSource2 = new LightSource(new Vector3(5, 0, 0), Color.GREEN);
        scene.addLightSource(lightSource2);
        lightSource2.setWork(Main.LIGHT2_WORK_ON_START);


        try {
//            IModel m = ObjLoader.obj2Model(new File("resources/Cube.obj"), true);
//            IModel m = ObjLoader.obj2Model(new File("resources/lowpolytree.obj"), true);
//            IModel m = ObjLoader.obj2Model(new File("resources/Wood_Table.obj"), true);
            IModel m = ObjLoader.obj2Model(new File("resources/Japanese_Temple.obj"), true);
//            IModel m = ObjLoader.obj2Model(new File("resources/Chevrolet_Camaro_SS_High.obj"), true);
//            IModel m = ObjLoader.obj2Model(new File("resources/Castle_OBJ.obj"), true);
//            IModel m = ObjLoader.obj2Model(new File("resources/Gibson 335_Low_Poly.obj"), true);
            System.out.println("Polygons: " + m.getLines().size());
            scene.models.add(m);
            scene.setSelectedModel(m);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


//        scene.models.add(new Cube(
//                new Vector3(-1, -1, -1),
//                new Vector3(1, 1, 1),
//                Color.RED)
//        );


        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(scene.drawScene(cam, sc),0,0, null);
    }
    private ScreenPoint last = null;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        last = new ScreenPoint(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        last = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint np = new ScreenPoint(e.getX(),e.getY());
        if(last != null){
            int dx = np.getI()-last.getI();
            int dy = np.getJ()-last.getJ();
            double a = dx*Math.PI/180;
            double b = dy*Math.PI/180;
            cam.rotate = Matrix4.rotate(-a,1).mul(Matrix4.rotate(-b,0)).mul(cam.rotate);
        }
        last = np;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            sc.setXr(sc.getXr() + CAMERA_SPEED);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            sc.setXr(sc.getXr() - CAMERA_SPEED);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            sc.setYr(sc.getYr() + CAMERA_SPEED);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            sc.setYr(sc.getYr() - CAMERA_SPEED);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_I){
            sc.zoomIn();
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_O){
            sc.zoomOut();
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            cam.rotate = Matrix4.rotate(-Math.PI/180, 1).mul(cam.rotate);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            cam.rotate = Matrix4.rotate(Math.PI/180, 1).mul(cam.rotate);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            cam.rotate = Matrix4.rotate(Math.PI/180, 0).mul(cam.rotate);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            cam.rotate = Matrix4.rotate(-Math.PI/180, 0).mul(cam.rotate);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_Q){
            cam.rotate = Matrix4.rotate(-Math.PI/180, 2).mul(cam.rotate);
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_E){
            cam.rotate = Matrix4.rotate(Math.PI/180, 2).mul(cam.rotate);
            repaint();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
