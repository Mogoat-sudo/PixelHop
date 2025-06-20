package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    public void update();
    public void draw(Graphics g);
    //Maus sachen
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    //Keyboard Sachen
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}
