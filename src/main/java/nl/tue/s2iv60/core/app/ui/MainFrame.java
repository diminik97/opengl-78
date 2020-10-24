package nl.tue.s2iv60.core.app.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import nl.tue.s2iv60.core.cg.Renderer;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Integer.MAX_VALUE;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import nl.tue.s2iv60.core.app.Base;
import nl.tue.s2iv60.core.app.GS;
import sandbox.Sandbox;
import sandbox.Sandbox.CheckBoxID;
import sandbox.Sandbox.ComboBoxID;
import sandbox.Sandbox.SliderID;

public class MainFrame {
    private JFrame frame;
     
    private JFrame infoFrame;
    private Renderer renderer;


    public MainFrame(Component canvas) { 
        fancyLookAndFeel();   // set better looking L&F
        
        frame = new JFrame(GS.APP_NAME);
        frame.getContentPane().setLayout(new BorderLayout());
        
        // create the settings panels and make them invisible by default
        JPanel checkBoxesPanel = getCheckBoxPanel();
        JPanel slidersPanel = getSlidersPanel();
        checkBoxesPanel.setVisible(false);
        slidersPanel.setVisible(false);
        
        // position canvas and add settings panels to canvas
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        JPanel panel;
        if (canvas instanceof JPanel) {
            panel = (JPanel)canvas;
            panel.setLayout(new FlowLayout(FlowLayout.TRAILING));  
            panel.add(slidersPanel);
            panel.add(checkBoxesPanel);
        } else {
            frame.add(slidersPanel,BorderLayout.EAST);
            frame.add(checkBoxesPanel,BorderLayout.WEST);
        } 
        
        // create and position the top bar
        frame.getContentPane().add(
                createTopBar(slidersPanel,checkBoxesPanel), 
                BorderLayout.NORTH
        );
        
        // attach the mouse handlers to the canvas
        MouseEventListener mouseEvent = new MouseEventListener();
        canvas.addMouseListener(mouseEvent);
        canvas.addMouseMotionListener(mouseEvent);
        canvas.addMouseWheelListener(mouseEvent);
        canvas.addKeyListener(new KeyEventListener());
        
        // set size of the whole frame/gui
        frame.setSize(GS.FRAME_WIDTH, GS.FRAME_HEIGHT);
        frame.setVisible(true);
        
        // stop application when window is closed
        frame.addWindowListener( new WindowAdapter() {
            @Override public void windowClosing( WindowEvent windowevent ) {
                frame.dispose();
                System.exit( 0 );
            }
        });
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
    
    private void fancyLookAndFeel() {   // Enable fancy GUI theme.
        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch ( ClassNotFoundException 
                | IllegalAccessException 
                | InstantiationException 
                | UnsupportedLookAndFeelException ex
        ) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* create settings panel with check boxes */
    private JPanel getCheckBoxPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        for (CheckBoxID id : CheckBoxID.values()) {
            JCheckBox cb = id.getWidget();
            cb.setMaximumSize(new Dimension(MAX_VALUE,cb.getPreferredSize().height));
            panel.add(cb);
        }
        panel.setMaximumSize(new Dimension(150,panel.getPreferredSize().height));
        return panel;
    }
    
    /* create settings panel with sliders */
     private JPanel getSlidersPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        for (SliderID id : SliderID.values()) {
            JSlider slider = id.getWidget();
            Box box = new Box(BoxLayout.X_AXIS);
            box.add(new JLabel(slider.getName()));
            box.add(slider);
            panel.add(box);
        }
        panel.setMaximumSize(new Dimension(150,panel.getPreferredSize().height));
        return panel;
    }
     
     /* create and fill the top bar; creating among others buttons to show/hide
      * the two given settings panels.
     */
    private JPanel createTopBar(JPanel slidersPanel, JPanel checkBoxesPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        addButtons(panel);
        panel.add(Box.createHorizontalStrut(10));
        addComboBoxes(panel);
        panel.add(Box.createHorizontalGlue());
        
        /* create the show/hide buttons for the settings panels */
        JButton slidersButton, checkBoxesButton;
        panel.add(slidersButton=new JButton("Sliders"));        
        panel.add(checkBoxesButton=new JButton("CheckBoxes"));
        slidersButton.setMaximumSize(slidersButton.getPreferredSize());        
        checkBoxesButton.setMaximumSize(checkBoxesButton.getPreferredSize());
        
        slidersButton.addActionListener(
                e -> slidersPanel.setVisible(!slidersPanel.isVisible())
        );
        checkBoxesButton.addActionListener(
                e->checkBoxesPanel.setVisible(!checkBoxesPanel.isVisible())
        );
        
        return panel;
    }
    
    /* add the comboboxes to the given panel. */
    private void addComboBoxes(JPanel panel) {
        for (ComboBoxID id : ComboBoxID.values()) {
            JComboBox cb = id.getWidget();
            cb.setMaximumSize(cb.getPreferredSize());
            panel.add(cb);
            panel.add(Box.createHorizontalStrut(10));
        }
    }

    /* add some buttons to the given panel */
    private void addButtons(JPanel panel) {        
        JButton glInfoButton = new JButton("Info");
        JButton resetButton = new JButton("Reset");

        glInfoButton.addActionListener( e -> {  // show/hide info Panel
                        if (infoFrame==null) {
                            infoFrame = new JFrame("OpenGL Info");
                            InfoPanel info = new InfoPanel(renderer.getGlInfo());
                            infoFrame.add(info);
                            infoFrame.pack();
                        }
                        infoFrame.setVisible(!infoFrame.isVisible());
                    }
        );

        resetButton.addActionListener( e -> renderer.resetView() );

        panel.add(glInfoButton);        
        panel.add(Box.createHorizontalStrut(10));
        panel.add(resetButton);
    }
}