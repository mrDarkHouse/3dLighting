package Panels;

import javax.swing.*;

public class Main {
    public static final int DRAW_WIDTH = 700;
    public static final int DRAW_HEIGHT = 700;
    public static final int CONTROL_WIDTH = 230;

    public static final boolean LIGHT1_WORK_ON_START = false;
    public static final boolean LIGHT2_WORK_ON_START = false;

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(DRAW_WIDTH + CONTROL_WIDTH, DRAW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DrawPanel draw = new DrawPanel();
        draw.setBounds(0, 0, DRAW_WIDTH, DRAW_HEIGHT);
        frame.add(draw);
        ControlPanel controlPanel = new ControlPanel(draw);
//        controlPanel.setFocusable(false);
        controlPanel.setBounds(DRAW_WIDTH, 0, CONTROL_WIDTH, DRAW_HEIGHT);
        frame.add(controlPanel);
        frame.setVisible(true);
    }
}
