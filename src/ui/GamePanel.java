package ui;

import logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 800, HEIGHT = 600;

    private Thread gameThread;
    private boolean running;

    private Player player;
    private Level level;
    private List<Platform> platforms;
    private GameStatus status;

    private double speedMultiplier = 1.0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(this);
        initGame();
        startGame();
    }

    private void initGame() {
        level = new Level();
        platforms = level.getPlatforms();
        player = new Player(level.getPlayerStartX(), level.getPlayerStartY());
        player.setSpeedMultiplier(speedMultiplier);
        status = GameStatus.RUNNING;
    }

    private void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        try {
            while (running) {
                update();
                repaint();
                Thread.sleep(16);
            }
        } catch (InterruptedException e) {
            running = false;
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fehler im Spiel:\n" + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            running = false;
        }
    }

    private void update() {
        if (status == GameStatus.RUNNING) {
            player.update(platforms);

            if (player.getX() > 750 && player.getY() < 220) {
                status = GameStatus.WON;
                speedMultiplier += 0.2;
                DialogHelper.showEndDialog(this, "Ziel erreicht! Glückwunsch!", this::restartGame);
            }

            if (player.getY() > HEIGHT) {
                status = GameStatus.LOST;
                speedMultiplier = 1.0;
                DialogHelper.showEndDialog(this, "Runtergefallen. Versuch's nochmal!", this::restartGame);
            }
        }
    }

    private void restartGame() {
        initGame();
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Platform p : platforms)
            p.draw(g);
        player.draw(g);

        for (int i = 0; i <platforms.size() ; i++) {
            System.out.println(platforms.get(i));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> player.setMovingLeft(true);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setMovingRight(true);
            case KeyEvent.VK_SPACE -> player.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> player.setMovingLeft(false);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setMovingRight(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
