package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;

// Verwaltet das Pause-Menü-Overlay mit allen Buttons und Einstellungen
public class PauseOverlay {
    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;
    private VolumeButton volumeButton;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeButton();
    }

    // Erstellt den Lautstärkeregler
    private void createVolumeButton() {
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    // Erstellt die Menü-, Neustart- und Fortsetzen-Buttons
    private void createUrmButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    // Erstellt die Musik- und Soundeffekt-Buttons
    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    // Lädt den Hintergrund und berechnet die Position
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    // Aktualisiert alle Buttons
    public void update() {
        musicButton.update();
        sfxButton.update();
        menuB.update();
        replayB.update();
        unpauseB.update();
        volumeButton.update();
    }

    // Zeichnet alle Komponenten des Pause-Menüs
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        musicButton.draw(g);
        sfxButton.draw(g);
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
        volumeButton.draw(g);
    }

    // Behandelt das Ziehen des Lautstärkereglers
    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
        }
    }

    // Setzt die mousePressed-Flags für die Buttons
    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton)) sfxButton.setMousePressed(true);
        else if (isIn(e, menuB)) menuB.setMousePressed(true);
        else if (isIn(e, replayB)) replayB.setMousePressed(true);
        else if (isIn(e, unpauseB)) unpauseB.setMousePressed(true);
        else if (isIn(e, volumeButton)) volumeButton.setMousePressed(true);
    }

    // Führt die Aktionen der Buttons aus
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton) && musicButton.isMousePressed())
            musicButton.setMuted(!musicButton.isMuted());
        else if (isIn(e, sfxButton) && sfxButton.isMousePressed())
            sfxButton.setMuted(!sfxButton.isMuted());
        else if (isIn(e, menuB) && menuB.isMousePressed()) {
            Gamestate.state = Gamestate.MENU;
            playing.unpauseGame();
        } else if (isIn(e, replayB) && replayB.isMousePressed())
            System.out.println("replay lvl!");
        else if (isIn(e, unpauseB) && unpauseB.isMousePressed())
            playing.unpauseGame();

        resetAllButtons();
    }

    // Setzt alle Buttons zurück
    private void resetAllButtons() {
        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
        volumeButton.resetBools();
    }

    // Aktualisiert die mouseOver-Flags für die Buttons
    public void mouseMoved(MouseEvent e) {
        resetMouseOverFlags();
        checkMouseOverButtons(e);
    }

    private void resetMouseOverFlags() {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);
    }

    private void checkMouseOverButtons(MouseEvent e) {
        if (isIn(e, musicButton)) musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton)) sfxButton.setMouseOver(true);
        else if (isIn(e, menuB)) menuB.setMouseOver(true);
        else if (isIn(e, replayB)) replayB.setMouseOver(true);
        else if (isIn(e, unpauseB)) unpauseB.setMouseOver(true);
        else if (isIn(e, volumeButton)) volumeButton.setMouseOver(true);
    }

    // Prüft ob die Maus über einem Button ist
    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}