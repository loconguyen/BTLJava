package app;

import javax.swing.SwingUtilities;
import presentation_layer.Login.loginFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginFrame login = new loginFrame();
            login.setVisible(true);
            
        });
    }
}