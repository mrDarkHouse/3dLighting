package Panels;

import Light.LightSource;

import Math.Vector3;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlPanel extends JComponent {

    private static final int COORD_LIMIT = 50*10;

    private DrawPanel panel;

    private class ObjectColorController extends JPanel{
        public ObjectColorController() {
            setLayout(new FlowLayout());
            TitledBorder b = BorderFactory.createTitledBorder("Object");
            setBorder(b);

            Color c = panel.getScene().getSelectedModel().getLines().get(0).getColor();

            JPanel color = new JPanel();
            color.setBackground(c);
            color.setOpaque(true);
            color.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if(evt.getButton() == MouseEvent.BUTTON1) {
                        Color initColor = color.getBackground();
                        Color choosedColor = JColorChooser.showDialog(ControlPanel.this,
                                "Choose JPanel Background Color", initColor);
                        color.setBackground(choosedColor);
                        panel.getScene().changeColorOfModel(choosedColor);
                        panel.repaint();
                        panel.requestFocus();
                    }
                }
            });
            add(color);
        }
    }

    private class GlobalLightController extends JPanel{
        public GlobalLightController() {
            setLayout(new FlowLayout());
            TitledBorder b = BorderFactory.createTitledBorder("Global light");
            setBorder(b);

            JSlider power = new JSlider(new DefaultBoundedRangeModel((int) (panel.getScene().getGlobalLight()*10),
                    1, 0, 10));
            power.addChangeListener(e -> {
                panel.getScene().setGlobalLight(power.getValue()/10f);
                panel.repaint();
                panel.requestFocus();
            });
            add(power);

        }
    }

    private class LightController extends JPanel{
        public LightController(int id) {
            setLayout(new FlowLayout());
            TitledBorder b = BorderFactory.createTitledBorder("Light " + (id + 1));
            setBorder(b);

            LightSource s = panel.getScene().getSources().get(id);
            JSlider x = new JSlider(new DefaultBoundedRangeModel(
                    (int) (s.getPosition().getX()*10), 2, -COORD_LIMIT, COORD_LIMIT));
            JSlider y = new JSlider(new DefaultBoundedRangeModel(
                    (int) (s.getPosition().getY()*10), 2, -COORD_LIMIT, COORD_LIMIT));
            JSlider z = new JSlider(new DefaultBoundedRangeModel(
                    (int) (s.getPosition().getZ()*10), 2, -COORD_LIMIT, COORD_LIMIT));

            x.addChangeListener(e -> {
                s.setPosition(new Vector3(x.getValue()/10D, s.getPosition().getY(), s.getPosition().getZ()));
                panel.repaint();
                panel.requestFocus();
            });
            y.addChangeListener(e -> {
                s.setPosition(new Vector3(s.getPosition().getX(), y.getValue()/10D, s.getPosition().getZ()));
                panel.repaint();
                panel.requestFocus();
            });
            z.addChangeListener(e -> {
                s.setPosition(new Vector3(s.getPosition().getX(), s.getPosition().getY(), z.getValue()/10D));
                panel.repaint();
                panel.requestFocus();
            });

            x.setSize(50, 20);
            y.setSize(50, 20);
            z.setSize(50, 20);
            JPanel color = new JPanel();
            color.setBackground(s.getColor());
            color.setOpaque(true);
            color.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if(evt.getButton() == MouseEvent.BUTTON1) {
                        Color initColor = color.getBackground();
                        Color choosedColor = JColorChooser.showDialog(ControlPanel.this,
                                "Choose JPanel Background Color", initColor);
                        color.setBackground(choosedColor);
                        s.setColor(choosedColor);
                        panel.repaint();
                        panel.requestFocus();
                    }
                }
            });

            JCheckBox work = new JCheckBox();
            work.setSelected(s.isWork());
            work.addChangeListener(e -> {
                s.setWork(work.isSelected());
                panel.repaint();
                panel.requestFocus();
            });

            add(new JLabel("x"));
            add(x);
            add(new JLabel("y"));
            add(y);
            add(new JLabel("z"));
            add(z);
            add(new JLabel("Color"));
            add(color);
            add(new JLabel("Work"));
            add(work);

        }
    }

    public ControlPanel(DrawPanel panel) {
        this.panel = panel;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        LightController light1 = new LightController(0);
        add(light1);
        LightController light2 = new LightController(1);
        add(light2);
        GlobalLightController gl = new GlobalLightController();
        add(gl);
        ObjectColorController controller = new ObjectColorController();
        add(controller);
    }
}
