package layouts;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * FlowLayout: los componentes van en fila con su tamaño natural y pasan a la
 * siguiente línea cuando no caben.
 */
public class PanelFlowLayout extends JPanel {

    public PanelFlowLayout() {
        super(new FlowLayout(FlowLayout.CENTER, 5, 5));
        setBorder(BorderFactory.createTitledBorder("FlowLayout"));
        Botones.ejemplo().forEach(this::add);
    }
}
