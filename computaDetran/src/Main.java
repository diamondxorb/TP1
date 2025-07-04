import utils.DatabaseConnection;
import utils.EstiloComputaDetran;
import view.LoginView;
import view.VistoriadorView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        //DatabaseConnection.criarTabelas();

        //setLookAndFeel determina como o programa vai aparentar
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            UIManager.put("Panel.background", Color.WHITE);
            UIManager.put("OptionPane.background", Color.WHITE);
            UIManager.put("Panel.foreground", EstiloComputaDetran.PRETO);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        //Roda a primeira tela
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });

        /*
        SwingUtilities.invokeLater(() -> {
            VistoriadorView vistoriadorView = new VistoriadorView("Júlia");
            vistoriadorView.setVisible(true);
        });
         */

    }
}