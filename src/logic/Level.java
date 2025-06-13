package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Level enthält Plattformen und Startposition.
 */
public class Level {
    private List<Platform> platforms;
    private int playerStartX, playerStartY;

    public Level() {
        platforms = new ArrayList<>();
        platforms.add(new Platform(0, 580, 800, 20));    // Böden
        platforms.add(new Platform(150, 450, 100, 20));
        platforms.add(new Platform(300, 350, 150, 20));
        platforms.add(new Platform(550, 300, 120, 20));
        platforms.add(new Platform(700, 220, 80, 20));

        playerStartX = 100;
        playerStartY = 500;
    }

    public List<Platform> getPlatforms() { return platforms; }
    public int getPlayerStartX() { return playerStartX; }
    public int getPlayerStartY() { return playerStartY; }
}