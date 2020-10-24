package nl.tue.s2iv60.core.app.ui;


import java.awt.event.KeyListener;
import nl.tue.s2iv60.core.app.GS;

public class KeyEventListener implements KeyListener {

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        GS.keysPressed.add(e.getKeyChar());
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        GS.keysPressed.remove(e.getKeyChar());
    }
}