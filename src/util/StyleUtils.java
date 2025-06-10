package util;

import javax.swing.*;
import java.awt.*;

public class StyleUtils {
    public static void applyDefaultStyle(JComponent component) {
        Font segoe = new Font("Segoe UI", Font.PLAIN, 14);
        component.setFont(segoe);

        if (component instanceof JButton) {
            component.setBackground(new Color(70, 130, 180));
            component.setForeground(Color.WHITE);
            ((JButton)component).setFocusPainted(false);
        }
    }

    public static void configureWindow(JFrame frame) {
        frame.getContentPane().setBackground(Color.WHITE);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }
}