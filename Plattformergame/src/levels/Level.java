package levels;

// Verwaltet die Leveldaten und Zugriff auf einzelne Kacheln
public class Level {
    private int[][] lvlData;  // Speichert die Levelstruktur als 2D-Array

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    // Gibt den Sprite-Index für eine bestimmte Position zurück
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    // Gibt die kompletten Leveldaten zurück
    public int[][] getLevelData() {
        return lvlData;
    }
}