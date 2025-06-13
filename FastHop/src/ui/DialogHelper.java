package ui;

import javax.swing.*;
import java.awt.*;

public class DialogHelper {
    /**
     * Zeigt das End-Dialogfenster an und führt Neustart/Beenden aus.
     */
    public static void showEndDialog(Component parent, String message, Runnable restartAction) {
        int option = JOptionPane.showOptionDialog(parent, message + "\nNochmal spielen?", "Spiel beendet",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (option == JOptionPane.YES_OPTION) {
            restartAction.run();
        } else {
            System.exit(0);
        }
    }
}