package logic;

import java.awt.*;

/**
 * Plattform-Objekt im Spiel.
 */
public class Platform extends GameObject {

    public Platform(int x, int y, int width, int height) {
        super(x, y, width, height); // Position und Größe an GameObject übergeben
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height); // Plattform als graues Rechteck zeichnen
    }

    public Rectangle getBounds() {
        // Rectangle repräsentiert die "Hitbox" der Plattform (für Kollisionserkennung)
        return new Rectangle(x, y, width, height);
    }
}
