package util;

import javax.swing.*;
import java.awt.*;

public class MotivoComboBoxRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);

        // Define o tooltip para cada item
        if (value != null) {
            label.setToolTipText(MotivosVistoria.getDescricao(value.toString()));
        }

        return label;
    }
}