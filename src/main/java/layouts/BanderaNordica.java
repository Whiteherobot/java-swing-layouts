package layouts;

import java.awt.Color;

/**
 * Banderas con cruz nórdica, descritas en celdas según sus proporciones
 * oficiales. La cruz está desplazada hacia el asta y puede tener un borde.
 */
enum BanderaNordica {

    //          filas cols  fila  col  ancho borde  fondo      borde      cruz
    NORUEGA("Noruega", 16, 22, 7, 7, 2, 1, 0xBA0C2F, 0xFFFFFF, 0x00205B),
    SUECIA("Suecia", 10, 16, 4, 5, 2, 0, 0x006AA7, 0, 0xFECC02),
    DINAMARCA("Dinamarca", 28, 37, 12, 12, 4, 0, 0xC8102E, 0, 0xFFFFFF),
    FINLANDIA("Finlandia", 11, 18, 4, 5, 3, 0, 0xFFFFFF, 0, 0x002F6C),
    ISLANDIA("Islandia", 18, 25, 8, 8, 2, 1, 0x02529C, 0xFFFFFF, 0xDC1E35);

    final String nombre;
    final int filas;
    final int columnas;
    final int filaCruz;
    final int columnaCruz;
    final int anchoCruz;
    final int borde;
    final Color fondo;
    final Color colorBorde;
    final Color colorCruz;

    BanderaNordica(String nombre, int filas, int columnas, int filaCruz, int columnaCruz, int anchoCruz,
                   int borde, int fondo, int colorBorde, int colorCruz) {
        this.nombre = nombre;
        this.filas = filas;
        this.columnas = columnas;
        this.filaCruz = filaCruz;
        this.columnaCruz = columnaCruz;
        this.anchoCruz = anchoCruz;
        this.borde = borde;
        this.fondo = new Color(fondo);
        this.colorBorde = new Color(colorBorde);
        this.colorCruz = new Color(colorCruz);
    }

    Color colorCelda(int fila, int col) {
        if (enFranja(fila, col, 0)) {
            return colorCruz;
        }
        if (borde > 0 && enFranja(fila, col, borde)) {
            return colorBorde;
        }
        return fondo;
    }

    /** Si la celda cae en la franja horizontal o vertical de la cruz, ensanchada en {@code extra}. */
    private boolean enFranja(int fila, int col, int extra) {
        boolean horizontal = fila >= filaCruz - extra && fila < filaCruz + anchoCruz + extra;
        boolean vertical = col >= columnaCruz - extra && col < columnaCruz + anchoCruz + extra;
        return horizontal || vertical;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
