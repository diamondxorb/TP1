import model.Proprietario;
import view.VistoriadorView;
import controller.SolicitacaoController;
import repository.SolicitacaoRepositoryMemoria;
import view.ProprietarioView;
import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Proprietario proprietarioLogado = new Proprietario("Ana", "07944435161", LocalDate.parse("2024-01-15"), "longe", "bilulu", "9999990992", "090909", "ana");

        // setLookAndFeel determina como o programa vai aparentar
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        /* Roda a primeira tela ProprietarioView*/
        SolicitacaoController controller = new SolicitacaoController(new SolicitacaoRepositoryMemoria());
        ProprietarioView tela = new ProprietarioView(controller, proprietarioLogado);
        tela.setVisible(true);

        /* Roda a primeira tela VistoriadorView
        SwingUtilities.invokeLater(() -> {
            VistoriadorView vistoriadorView = new VistoriadorView("Júlia");
            vistoriadorView.setVisible(true);
        });*/
    }
}
