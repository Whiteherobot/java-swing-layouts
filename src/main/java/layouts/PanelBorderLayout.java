package layouts;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

/**
 * BorderLayout: cinco zonas. El centro se queda con todo el espacio que sobra.
 */
public class PanelBorderLayout extends JPanel {

    public PanelBorderLayout() {
        super(new BorderLayout(5, 5));
        setBorder(BorderFactory.createTitledBorder("BorderLayout"));
        List<JButton> b = Botones.ejemplo();
        add(b.get(0), BorderLayout.PAGE_START);
        add(b.get(1), BorderLayout.CENTER);
        add(b.get(2), BorderLayout.LINE_START);
        add(b.get(3), BorderLayout.PAGE_END);
        add(b.get(4), BorderLayout.LINE_END);
    }
}
