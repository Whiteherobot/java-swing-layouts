package layouts;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.FlowLayout;

/**
 * FlowLayout: los componentes van en fila con su tamaño natural y pasan a la
 * siguiente línea cuando no caben. Achicar la ventana muestra el salto.
 */
class PanelFlowLayout extends PanelDemo {

    enum Alineacion {
        IZQUIERDA("LEADING", FlowLayout.LEADING),
        CENTRO("CENTER", FlowLayout.CENTER),
        DERECHA("TRAILING", FlowLayout.TRAILING);

        final String constante;
        final int valor;

        Alineacion(String constante, int valor) {
            this.constante = constante;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    final JComboBox<Alineacion> alineacion = new JComboBox<>(Alineacion.values());
    final JSpinner botones = spinner(5, 1, 16);
    final JSpinner separacion = spinner(5, 0, 30);

    PanelFlowLayout() {
        alineacion.setSelectedItem(Alineacion.CENTRO);
        alineacion.addActionListener(e -> actualizar());
        agregarControl("Alineación", alineacion);
        agregarControl("Botones", botones);
        agregarControl("Separación", separacion);
        cerrarControles();
        actualizar();
    }

    private Alineacion alineacionActual() {
        return (Alineacion) alineacion.getSelectedItem();
    }

    @Override
    protected void construir() {
        vista.setLayout(new FlowLayout(alineacionActual().valor, valor(separacion), valor(separacion)));
        for (int i = 1; i <= valor(botones); i++) {
            vista.add(boton(i));
        }
    }

    @Override
    protected String codigo() {
        return String.format("panel.setLayout(new FlowLayout(FlowLayout.%s, %d, %d));%n"
                        + "for (int i = 1; i <= %d; i++) panel.add(new JButton(\"Button \" + i));",
                alineacionActual().constante, valor(separacion), valor(separacion), valor(botones));
    }
}
