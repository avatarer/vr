package runfast;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * All rights belong to HighWay Company
 * User: Yuriy
 * Date: 28.10.13
 * Time: 8:42
 */
public class HardDialog {
    public static void exit(String message) {
        JOptionPane.showMessageDialog(null, message);
        System.exit(1);
    }

    public static void approve(String message) {
        int i = JOptionPane.showConfirmDialog(null, "Вход выполнен?");
        if (i != JOptionPane.YES_OPTION) {
            exit("Сам виноват");
        }
    }
}
