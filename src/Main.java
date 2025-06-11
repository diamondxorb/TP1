import repository.SolicitacaoRepositoryMemoria;
import view.VistoriadorView;
import controller.SolicitacaoController;
import view.ProprietarioView;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // setLookAndFeel determina como o programa vai aparentar
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        /* Roda a primeira tela ProprietarioView*/
        SolicitacaoController controller = new SolicitacaoController(new SolicitacaoRepositoryMemoria());
        ProprietarioView tela = new ProprietarioView(controller);
        tela.setVisible(true);

        /* Roda a primeira tela VistoriadorView
        SwingUtilities.invokeLater(() -> {
            VistoriadorView vistoriadorView = new VistoriadorView("Júlia");
            vistoriadorView.setVisible(true);
        });*/

    }
}

