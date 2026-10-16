package layouts;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Estructura común de cada pestaña: vista previa al centro, controles a la
 * derecha y abajo el código equivalente y el estado.
 */
abstract class PanelDemo extends JPanel {

    static final String AYUDA = "Haga clic en un botón para ver su tamaño y posición.";

    protected final JPanel vista = new JPanel();
    private final JPanel controles = new JPanel(new GridBagLayout());
    private final JTextArea codigo = new JTextArea(4, 40);
    private final JLabel estado = new JLabel(AYUDA);
    private int filaControl;

    PanelDemo() {
        super(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        vista.setBorder(BorderFactory.createTitledBorder("Vista previa"));
        controles.setBorder(BorderFactory.createTitledBorder("Controles"));

        codigo.setEditable(false);
        codigo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        codigo.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        JPanel inferior = new JPanel(new BorderLayout(0, 4));
        inferior.add(codigo, BorderLayout.CENTER);
        inferior.add(estado, BorderLayout.PAGE_END);
        inferior.setBorder(BorderFactory.createTitledBorder("Código equivalente"));

        add(vista, BorderLayout.CENTER);
        add(controles, BorderLayout.LINE_END);
        add(inferior, BorderLayout.PAGE_END);
    }

    /** Vuelve a armar la vista previa con el layout y los componentes actuales. */
    protected abstract void construir();

    /** Código Java que produce la vista previa actual. */
    protected abstract String codigo();

    protected final void actualizar() {
        vista.removeAll();
        construir();
        vista.revalidate();
        vista.repaint();
        codigo.setText(codigo());
        estado.setText(ayuda());
    }

    /** Texto de ayuda que se muestra bajo el código. */
    protected String ayuda() {
        return AYUDA;
    }

    protected final void agregarControl(String etiqueta, JComponent control) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = filaControl++;
        c.insets = new Insets(3, 6, 3, 6);
        c.anchor = GridBagConstraints.LINE_END;
        controles.add(new JLabel(etiqueta), c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        controles.add(control, c);
    }

    /** Termina la columna de controles para que queden arriba. */
    protected final void cerrarControles() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = filaControl;
        c.weighty = 1;
        controles.add(new JLabel(), c);
    }

    protected final JSpinner spinner(int valor, int minimo, int maximo) {
        JSpinner s = new JSpinner(new SpinnerNumberModel(valor, minimo, maximo, 1));
        s.addChangeListener(e -> actualizar());
        return s;
    }

    /** Botón de ejemplo que, al pulsarlo, informa el espacio que le dio el layout. */
    protected final JButton boton(int numero) {
        JButton b = new JButton(Botones.texto(numero));
        b.addActionListener(e -> mostrarMedidas(b));
        return b;
    }

    void mostrarMedidas(JButton b) {
        estado.setText(String.format("%s: %d × %d px, en la posición (%d, %d) de la vista previa.",
                b.getText(), b.getWidth(), b.getHeight(), b.getX(), b.getY()));
    }

    protected final void avisar(String mensaje) {
        estado.setText(mensaje);
    }

    static int valor(JSpinner s) {
        return (Integer) s.getValue();
    }

    String getCodigo() {
        return codigo.getText();
    }

    String getEstado() {
        return estado.getText();
    }
}
