package layouts;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Explorador de layouts: una pestaña por layout y una para las banderas.
 */
public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        super("Explorador de layouts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(crearPestanias());
        setSize(820, 560);
        setLocationRelativeTo(null);
    }

    static JTabbedPane crearPestanias() {
        JTabbedPane pestanias = new JTabbedPane();
        pestanias.addTab("BorderLayout", new PanelBorderLayout());
        pestanias.addTab("GridLayout", new PanelGridLayout());
        pestanias.addTab("FlowLayout", new PanelFlowLayout());
        pestanias.addTab("Banderas", new PanelBandera());
        return pestanias;
    }

    public static void main(String[] args) {
        // Swing no es thread-safe: la interfaz se crea en el hilo de eventos.
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ignorada) {
                // Si no hay look and feel del sistema se usa el de Java.
            }
            new VentanaPrincipal().setVisible(true);
        });
    }
}
