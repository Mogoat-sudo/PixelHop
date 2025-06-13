package logic;

import java.awt.*;
import java.util.List;

public class Player extends GameObject {

    private int speedX = 0;
    private int speedY = 0;

    private boolean moveLeft = false;
    private boolean moveRight = false;

    private boolean onGround = true;

    private final int JUMP_POWER = 17;
    private final int GRAVITY = 1;

    // Basis-Geschwindigkeit (wird mit Multiplikator skaliert)
    private final int BASE_MOVE_SPEED = 5;

    // Neuer Speed-Multiplikator (Standard 1.0)
    private double speedMultiplier = 1.0;

    public Player(int startX, int startY) {
        super(startX, startY, 40, 60);
    }

    public void update(List<Platform> platforms) {
        int moveSpeed = (int)(BASE_MOVE_SPEED * speedMultiplier);

        if (moveLeft) speedX = -moveSpeed;
        else if (moveRight) speedX = moveSpeed;
        else speedX = 0;

        x += speedX;

        // Kollision horizontal
        for (Platform p : platforms) {
            if (getBounds().intersects(p.getBounds())) {
                if (speedX > 0) {
                    x = p.getX() - width;
                } else if (speedX < 0) {
                    x = p.getX() + p.getWidth();
                }
                speedX = 0;
            }
        }

        speedY += GRAVITY;
        y += speedY;
        onGround = false;

        // Kollision vertikal
        for (Platform p : platforms) {
            if (getBounds().intersects(p.getBounds())) {
                if (speedY > 0) {
                    y = p.getY() - height;
                    speedY = 0;
                    onGround = true;
                } else if (speedY < 0) {
                    y = p.getY() + p.getHeight();
                    speedY = 0;
                }
            }
        }
    }

    public void jump() {
        if (onGround) {
            speedY = -JUMP_POWER;
            onGround = false;
        }
    }

    public void setMovingLeft(boolean b) {
        moveLeft = b;
    }

    public void setMovingRight(boolean b) {
        moveRight = b;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setSpeedMultiplier(double multiplier) {
        this.speedMultiplier = multiplier;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
