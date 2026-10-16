package layouts;

import javax.swing.JSpinner;
import java.awt.GridLayout;

/**
 * GridLayout: celdas del mismo tamaño. Filas o columnas en 0 significa
 * "las que hagan falta"; las dos en 0 no se permite.
 */
class PanelGridLayout extends PanelDemo {

    final JSpinner filas = spinner(0, 0, 8);
    final JSpinner columnas = spinner(2, 0, 8);
    final JSpinner botones = spinner(5, 1, 16);
    final JSpinner separacion = spinner(5, 0, 30);

    PanelGridLayout() {
        agregarControl("Filas (0 = automático)", filas);
        agregarControl("Columnas (0 = automático)", columnas);
        agregarControl("Botones", botones);
        agregarControl("Separación", separacion);
        cerrarControles();
        actualizar();
    }

    @Override
    protected void construir() {
        if (valor(filas) == 0 && valor(columnas) == 0) {
            // GridLayout lanza una excepción con 0 filas y 0 columnas.
            columnas.setValue(1);
            return;
        }
        vista.setLayout(new GridLayout(valor(filas), valor(columnas), valor(separacion), valor(separacion)));
        for (int i = 1; i <= valor(botones); i++) {
            vista.add(boton(i));
        }
    }

    @Override
    protected String codigo() {
        String codigo = String.format("panel.setLayout(new GridLayout(%d, %d, %d, %d));%n"
                        + "for (int i = 1; i <= %d; i++) panel.add(new JButton(\"Button \" + i));",
                valor(filas), valor(columnas), valor(separacion), valor(separacion), valor(botones));
        if (valor(filas) > 0 && valor(columnas) > 0) {
            codigo += "\n// Con filas > 0, Java respeta las filas y recalcula las columnas.";
        }
        return codigo;
    }

    /** Columnas que realmente usa GridLayout con la configuración actual. */
    int columnasEfectivas() {
        int f = valor(filas);
        int n = valor(botones);
        return f > 0 ? (n + f - 1) / f : valor(columnas);
    }
}
