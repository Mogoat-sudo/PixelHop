package main;

import entities.Player;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import levels.LevelManager;

import javax.swing.*;
import java.awt.*;

// Hauptspielklasse, verwaltet den Spielablauf und die Spielschleife
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;  // Ziel-Bildrate
    private final int UPS_SET = 200;  // Aktualisierungen pro Sekunde

    private Playing playing;
    private Menu menu;

    // Konstanten für Spielfeldgröße und Skalierung
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private Player player;
    private LevelManager levelManager;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    // Initialisiert Menü und Spielzustand
    public void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    // Startet die Spielschleife in einem eigenen Thread
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Aktualisiert den aktiven Spielzustand
    public void update() {
        switch (Gamestate.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
        }
    }

    // Zeichnet den aktiven Spielzustand
    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
        }
    }

    // Hauptspielschleife mit festgelegter Aktualisierungs- und Bildrate
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // Aktualisiere Spiellogik
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            // Zeichne Frame
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // Zeige FPS und UPS einmal pro Sekunde
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    // Behandelt Fokusverlust des Fensters
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING)
            playing.getPlayer().resetDirBooleans();
    }

    // Getter
    public Menu getMenu() { return menu; }
    public Playing getPlaying() { return playing; }
}