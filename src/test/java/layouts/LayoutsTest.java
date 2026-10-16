package layouts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LayoutsTest {

    @Test
    void borderLayoutUbicaCadaBotonYPermiteQuitarZonas() {
        PanelBorderLayout panel = new PanelBorderLayout();
        BorderLayout layout = assertInstanceOf(BorderLayout.class, panel.vista.getLayout());
        assertEquals("Button 2", ((JButton) layout.getLayoutComponent(BorderLayout.CENTER)).getText());
        assertEquals("Long-Named Button 4", ((JButton) layout.getLayoutComponent(BorderLayout.PAGE_END)).getText());

        panel.activas.get(PanelBorderLayout.Zona.CENTER).doClick();
        layout = (BorderLayout) panel.vista.getLayout();
        assertNull(layout.getLayoutComponent(BorderLayout.CENTER));
        assertEquals(4, panel.vista.getComponentCount());
        assertFalse(panel.getCodigo().contains("CENTER"));
    }

    @Test
    void gridLayoutGeneraElCodigoYEvitaCeroFilasYColumnas() {
        PanelGridLayout panel = new PanelGridLayout();
        assertTrue(panel.getCodigo().startsWith("panel.setLayout(new GridLayout(0, 2, 5, 5));"));
        panel.botones.setValue(7);
        assertEquals(7, panel.vista.getComponentCount());

        panel.columnas.setValue(0);
        assertEquals(1, panel.columnas.getValue());
        assertInstanceOf(GridLayout.class, panel.vista.getLayout());
    }

    @Test
    void gridLayoutConFilasRecalculaLasColumnas() {
        PanelGridLayout panel = new PanelGridLayout();
        panel.filas.setValue(2);
        panel.columnas.setValue(5);
        panel.botones.setValue(7);
        assertEquals(4, panel.columnasEfectivas());
        assertTrue(panel.getCodigo().contains("recalcula las columnas"));
    }

    @Test
    void flowLayoutCambiaLaAlineacion() {
        PanelFlowLayout panel = new PanelFlowLayout();
        panel.alineacion.setSelectedItem(PanelFlowLayout.Alineacion.DERECHA);
        FlowLayout layout = assertInstanceOf(FlowLayout.class, panel.vista.getLayout());
        assertEquals(FlowLayout.TRAILING, layout.getAlignment());
        assertTrue(panel.getCodigo().contains("FlowLayout.TRAILING"));
    }

    @Test
    void elBotonInformaSusMedidas() {
        PanelFlowLayout panel = new PanelFlowLayout();
        JButton boton = (JButton) panel.vista.getComponent(0);
        boton.setBounds(10, 20, 90, 30);
        boton.doClick();
        assertEquals("Button 1: 90 × 30 px, en la posición (10, 20) de la vista previa.", panel.getEstado());
    }

    /** Celdas de la cruz (sin borde) = ancho·(filas + columnas) − ancho². */
    @ParameterizedTest
    @CsvSource({"NORUEGA, 72", "SUECIA, 48", "DINAMARCA, 244", "FINLANDIA, 78", "ISLANDIA, 82"})
    void cadaBanderaTieneLaCruzDelTamanioCorrecto(BanderaNordica b, int celdasCruz) {
        int cruz = 0;
        for (int f = 0; f < b.filas; f++) {
            for (int c = 0; c < b.columnas; c++) {
                if (b.colorCelda(f, c).equals(b.colorCruz)) {
                    cruz++;
                }
            }
        }
        assertEquals(celdasCruz, cruz);
    }

    @Test
    void noruegaRespetaSusFranjas() {
        BanderaNordica n = BanderaNordica.NORUEGA;
        Color rojo = n.fondo;
        assertEquals(rojo, n.colorCelda(0, 0));
        assertEquals(Color.WHITE, n.colorCelda(0, 6));
        assertEquals(n.colorCruz, n.colorCelda(0, 7));
        assertEquals(n.colorCruz, n.colorCelda(8, 21));
        assertEquals(rojo, n.colorCelda(15, 21));
    }

    @Test
    void laPestaniaDeBanderasCambiaLaCuadricula() {
        PanelBandera panel = new PanelBandera();
        panel.bandera.setSelectedItem(BanderaNordica.DINAMARCA);
        assertTrue(panel.getCodigo().startsWith("panel.setLayout(new GridLayout(28, 37));"));
    }
}
