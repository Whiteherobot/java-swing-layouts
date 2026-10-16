package layouts;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * GridLayout: celdas del mismo tamaño. Con 0 filas y 2 columnas, las filas
 * se agregan según la cantidad de componentes.
 */
public class PanelGridLayout extends JPanel {

    public PanelGridLayout() {
        super(new GridLayout(0, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("GridLayout"));
        Botones.ejemplo().forEach(this::add);
    }
}
