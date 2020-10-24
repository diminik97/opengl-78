package sandbox;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import nl.tue.s2iv60.core.app.Base;


/**
 * 2IV60 - Computer Graphics
 * Date: 01/09/2020
 * @author kafoe
 */
public class Sandbox extends Base {
    /** add an enum item here to get an additional slider. 
     * Pickup int value of the slider in [0,100] by e.g. <code>SLIDER1.getValue();</code>.
     **/
    public enum SliderID {    
        SLIDER1("A",50), SLIDER2("B",50);
        private JSlider slider;
        SliderID(String label,int value) {
            slider = new JSlider(0,100, value);
            slider.setName(label);
        }
        public int getValue() { return slider==null?0:slider.getValue(); }
        public JSlider getWidget() { return slider; }
    }
    
    /** add an enum item here to get an additional combobox. 
     * Pickup int value of combobox by e.g. <code>VIEWMODE.getValue();</code>.
     * The int value is the index of the choice made, starting from 0.
     **/
    public enum ComboBoxID {        
        VIEWMODE(0, "Fixed", "Flying camera", "First person camera"), 
        DAYNIGHT(0, "Day", "Night");
        private JComboBox cb;
        ComboBoxID(int initialChoice, String ... choices) { 
            this.cb = new JComboBox(choices); 
            cb.setSelectedIndex(initialChoice);
            cb.addActionListener(  // print value when it changes
                e->System.err.format("checkbox <%s>: %s (%d)\n",
                        toString(), cb.getSelectedItem(), cb.getSelectedIndex())
            );
        }
        public int getValue() { return cb==null?-1:cb.getSelectedIndex(); }
        
        public JComboBox getWidget() { return cb; }
    }
    
    /** add an enum item here to get an additional checkbox. 
     * * Pickup boolean value of checkbox by e.g. <code>SHOWAXIS.getValue();</code>.
     **/
    public enum CheckBoxID {
        SHOWAXIS("Show axis", true),
        SHOWTERRAIN("Show terrain",true),
        SHOWCAROUSEL("Show carousel",true),
        SHOWPATH("Show path", true),
        SHOWCONTROLPOLYGONS("show control polygons",false),
        SHOWSTALL("Show stall",true),
        SHOWLIGHTPOLES("Show light poles",true),
        LIGHTPOLESON("lightpoles on",false),
        HEADLIGHTON("head light on", true);

        private JCheckBox cb;
        CheckBoxID(String label, boolean initialValue) { 
            this.cb = new JCheckBox(label, initialValue); 
            this.cb.addActionListener(  // print value when it changes
               e->System.err.format("checkbox <%s>: %b\n",toString(),cb.isSelected())
            );
        }
        public boolean getValue() { return cb==null?false:cb.isSelected(); }
        public JCheckBox getWidget() { return cb; }
    }
    
    public static void main(String[] args) {
        Sandbox sandBox = new Sandbox();
        sandBox.setRenderer(new SandboxRenderer());
        sandBox.start();
    }
}
