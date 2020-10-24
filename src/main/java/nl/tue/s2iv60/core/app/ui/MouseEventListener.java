package nl.tue.s2iv60.core.app.ui;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import nl.tue.s2iv60.core.app.GS;

class MouseEventListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    // Minimum value of phi.
    static public float PHI_MIN = -(float) Math.PI / 2f + 0.01f;

    // Maximum value of phi.
    static public float PHI_MAX = (float) Math.PI / 2f - 0.01f;

    // Ratio of distance in pixels dragged and radial change of camera.
    static public float DRAG_PIXEL_TO_RADIAN = 0.025f;

    // Minimum distance of camera to center point.
    static public float MIN_CAMERA_DISTANCE = 1f;

    // Distance multiplier per mouse wheel tick.
    static public float MOUSE_WHEEL_FACTOR = 1.2f;
    
    // Position of mouse drag source.
    private int dragSourceX, dragSourceY;

    // Last mouse button pressed.
    private int mouseButton;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        dragSourceX = e.getX();
        dragSourceY = e.getY();
        mouseButton = e.getButton();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        float dX = e.getX() - dragSourceX;
        float dY = e.getY() - dragSourceY;
        
        // compensate for up-side down axes system on display
        dY = -dY;
        
        // Change camera angle when left button is pressed.
        if(mouseButton == java.awt.event.MouseEvent.BUTTON1) {
            // mouse to the right, dX >0, then theta smaller,
            // resulting in scene turning against the clock :-)
            GS.theta -= dX * DRAG_PIXEL_TO_RADIAN;  
            // similar for dY and phi, hence again phi becomes smaller if dY > 0
            GS.phi = Math.max(PHI_MIN,
                    Math.min(PHI_MAX,
                            GS.phi - dY * DRAG_PIXEL_TO_RADIAN));

        }
        // Change vWidth when right button is pressed.
        else if(mouseButton == java.awt.event.MouseEvent.BUTTON3) {
            // removed gs.vWidth from application
                /*
                gs.vWidth = Math.max(VWIDTH_MIN,
                                     Math.min(VWIDTH_MAX,
                                              gs.vWidth + dY * DRAG_PIXEL_TO_VWIDTH));
                */
        }

        dragSourceX = e.getX();
        dragSourceY = e.getY();
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        GS.vDist = (float) Math.max(MIN_CAMERA_DISTANCE,
                GS.vDist *
                        Math.pow(MOUSE_WHEEL_FACTOR,
                                e.getWheelRotation()));
    }
}
