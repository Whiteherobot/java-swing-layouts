package layouts;

import org.junit.jupiter.api.Test;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class LayoutsTest {

    @Test
    void borderLayoutUbicaCadaBotonEnSuZona() {
        PanelBorderLayout panel = new PanelBorderLayout();
        BorderLayout layout = assertInstanceOf(BorderLayout.class, panel.getLayout());
        assertEquals("Button 1", ((JButton) layout.getLayoutComponent(BorderLayout.PAGE_START)).getText());
        assertEquals("Button 2", ((JButton) layout.getLayoutComponent(BorderLayout.CENTER)).getText());
        assertEquals("Button 3", ((JButton) layout.getLayoutComponent(BorderLayout.LINE_START)).getText());
        assertEquals("Long-Named Button 4", ((JButton) layout.getLayoutComponent(BorderLayout.PAGE_END)).getText());
        assertEquals("Button 5", ((JButton) layout.getLayoutComponent(BorderLayout.LINE_END)).getText());
    }

    @Test
    void gridYFlowTienenLosCincoBotonesEnOrden() {
        PanelGridLayout grid = new PanelGridLayout();
        assertEquals(2, assertInstanceOf(GridLayout.class, grid.getLayout()).getColumns());
        assertEquals(5, grid.getComponentCount());
        assertEquals("Button 1", ((JButton) grid.getComponent(0)).getText());

        PanelFlowLayout flow = new PanelFlowLayout();
        assertInstanceOf(FlowLayout.class, flow.getLayout());
        assertEquals("Button 5", ((JButton) flow.getComponent(4)).getText());
    }

    @Test
    void banderaRespetaLasProporcionesDeNoruega() {
        int rojas = 0;
        int blancas = 0;
        int azules = 0;
        for (int f = 0; f < PanelBandera.FILAS; f++) {
            for (int c = 0; c < PanelBandera.COLUMNAS; c++) {
                Color color = PanelBandera.colorCelda(f, c);
                if (color.equals(PanelBandera.ROJO)) {
                    rojas++;
                } else if (color.equals(PanelBandera.BLANCO)) {
                    blancas++;
                } else {
                    azules++;
                }
            }
        }
        // Rojo: (6 + 12) × (6 + 6) = 216. Azul: cruz de 2 de ancho = 2·22 + 2·16 − 4 = 72.
        assertEquals(216, rojas);
        assertEquals(72, azules);
        assertEquals(16 * 22 - 216 - 72, blancas);
    }

    @Test
    void cruzDesplazadaHaciaElAsta() {
        assertEquals(PanelBandera.ROJO, PanelBandera.colorCelda(0, 0));
        assertEquals(PanelBandera.BLANCO, PanelBandera.colorCelda(0, 6));
        assertEquals(PanelBandera.AZUL, PanelBandera.colorCelda(0, 7));
        assertEquals(PanelBandera.AZUL, PanelBandera.colorCelda(8, 21));
        assertEquals(PanelBandera.ROJO, PanelBandera.colorCelda(15, 21));
    }
}
