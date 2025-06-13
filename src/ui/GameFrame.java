package ui;

import javax.swing.JFrame;

/**
 * Hauptfenster des Spiels.
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Mini Plattformer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}