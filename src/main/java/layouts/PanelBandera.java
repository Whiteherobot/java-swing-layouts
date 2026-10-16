package layouts;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

/**
 * Bandera de Noruega armada con un GridLayout de 16 × 22 celdas, que son sus
 * proporciones oficiales: verticalmente 6-1-2-1-6 y horizontalmente 6-1-2-1-12
 * (rojo, blanco, azul, blanco, rojo).
 */
public class PanelBandera extends JPanel {

    public static final int FILAS = 16;
    public static final int COLUMNAS = 22;
    static final Color ROJO = new Color(0xBA0C2F);
    static final Color BLANCO = Color.WHITE;
    static final Color AZUL = new Color(0x00205B);

    private static final int TAMANIO_CELDA = 18;

    public PanelBandera() {
        super(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Bandera de Noruega (GridLayout 16 × 22)"));

        JPanel bandera = new JPanel(new GridLayout(FILAS, COLUMNAS));
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                JPanel celda = new JPanel();
                celda.setBackground(colorCelda(fila, col));
                bandera.add(celda);
            }
        }
        // GridBagLayout sin restricciones centra la bandera y respeta su tamaño,
        // así la proporción no se deforma al cambiar el tamaño de la ventana.
        bandera.setPreferredSize(new Dimension(COLUMNAS * TAMANIO_CELDA, FILAS * TAMANIO_CELDA));
        add(bandera);
    }

    /** Color de la celda según la cruz nórdica: azul al centro, borde blanco y fondo rojo. */
    static Color colorCelda(int fila, int col) {
        boolean franjaAzul = fila == 7 || fila == 8 || col == 7 || col == 8;
        boolean franjaBlanca = (fila >= 6 && fila <= 9) || (col >= 6 && col <= 9);
        if (franjaAzul) {
            return AZUL;
        }
        return franjaBlanca ? BLANCO : ROJO;
    }
}
