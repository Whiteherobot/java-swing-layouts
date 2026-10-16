package layouts;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * BorderLayout: cinco zonas. Al quitar zonas se ve que el centro siempre se
 * queda con el espacio libre y los bordes solo con su tamaño preferido.
 */
class PanelBorderLayout extends PanelDemo {

    /** Zonas con el botón que ocupa cada una, como en el tutorial de Java. */
    enum Zona {
        PAGE_START(BorderLayout.PAGE_START, 1),
        LINE_START(BorderLayout.LINE_START, 3),
        CENTER(BorderLayout.CENTER, 2),
        LINE_END(BorderLayout.LINE_END, 5),
        PAGE_END(BorderLayout.PAGE_END, 4);

        final String constante;
        final int boton;

        Zona(String constante, int boton) {
            this.constante = constante;
            this.boton = boton;
        }
    }

    final Map<Zona, JCheckBox> activas = new LinkedHashMap<>();
    final JSpinner separacionH = spinner(5, 0, 30);
    final JSpinner separacionV = spinner(5, 0, 30);

    PanelBorderLayout() {
        for (Zona zona : Zona.values()) {
            JCheckBox check = new JCheckBox(zona.name(), true);
            check.addActionListener(e -> actualizar());
            activas.put(zona, check);
            agregarControl(zona == Zona.PAGE_START ? "Zonas" : "", check);
        }
        agregarControl("Separación horizontal", separacionH);
        agregarControl("Separación vertical", separacionV);
        cerrarControles();
        actualizar();
    }

    @Override
    protected void construir() {
        vista.setLayout(new BorderLayout(valor(separacionH), valor(separacionV)));
        for (Zona zona : Zona.values()) {
            if (activas.get(zona).isSelected()) {
                vista.add(boton(zona.boton), zona.constante);
            }
        }
    }

    @Override
    protected String codigo() {
        StringBuilder sb = new StringBuilder(String.format("panel.setLayout(new BorderLayout(%d, %d));%n",
                valor(separacionH), valor(separacionV)));
        for (Zona zona : Zona.values()) {
            if (activas.get(zona).isSelected()) {
                sb.append(String.format("panel.add(new JButton(\"%s\"), BorderLayout.%s);%n",
                        Botones.texto(zona.boton), zona.name()));
            }
        }
        return sb.toString().strip();
    }
}
