package layouts;

/**
 * Textos de los botones de ejemplo: los del tutorial clásico de layouts de Java,
 * donde el cuarto tiene un nombre largo para notar cómo cada layout maneja los anchos.
 */
final class Botones {

    private Botones() {
    }

    static String texto(int numero) {
        return numero == 4 ? "Long-Named Button 4" : "Button " + numero;
    }
}
