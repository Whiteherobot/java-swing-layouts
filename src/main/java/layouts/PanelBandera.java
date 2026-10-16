package layouts;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

/**
 * Banderas nórdicas armadas con un GridLayout: cada celda es un panel de color.
 */
class PanelBandera extends PanelDemo {

    private static final int ANCHO_MAXIMO = 440;
    private static final int ALTO_MAXIMO = 300;

    final JComboBox<BanderaNordica> bandera = new JComboBox<>(BanderaNordica.values());

    PanelBandera() {
        bandera.addActionListener(e -> actualizar());
        agregarControl("Bandera", bandera);
        cerrarControles();
        actualizar();
    }

    BanderaNordica actual() {
        return (BanderaNordica) bandera.getSelectedItem();
    }

    @Override
    protected void construir() {
        BanderaNordica b = actual();
        JPanel cuadricula = new JPanel(new GridLayout(b.filas, b.columnas));
        for (int f = 0; f < b.filas; f++) {
            for (int c = 0; c < b.columnas; c++) {
                JPanel celda = new JPanel();
                celda.setBackground(b.colorCelda(f, c));
                cuadricula.add(celda);
            }
        }
        // Todas las celdas miden lo mismo: así se conserva la proporción de la bandera.
        int celda = Math.max(4, Math.min(ANCHO_MAXIMO / b.columnas, ALTO_MAXIMO / b.filas));
        cuadricula.setPreferredSize(new Dimension(b.columnas * celda, b.filas * celda));
        // GridBagLayout sin restricciones centra la bandera sin estirarla.
        vista.setLayout(new GridBagLayout());
        vista.add(cuadricula);
    }

    @Override
    protected String ayuda() {
        return "Cada celda es un JPanel de color. Cambie el tamaño de la ventana: la proporción se mantiene.";
    }

    @Override
    protected String codigo() {
        BanderaNordica b = actual();
        return String.format("panel.setLayout(new GridLayout(%d, %d));   // %d celdas, proporción %d:%d%n"
                        + "for (cada fila y columna) panel.add(celdaDeColor(fila, columna));%n"
                        + "// Cruz desde la columna %d y la fila %d, %d celdas de ancho%s.",
                b.filas, b.columnas, b.filas * b.columnas, b.columnas, b.filas,
                b.columnaCruz, b.filaCruz, b.anchoCruz,
                b.borde > 0 ? ", con borde de " + b.borde : "");
    }
}
