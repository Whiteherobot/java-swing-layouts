package layouts;

import javax.swing.JButton;
import java.util.List;

/**
 * Los cinco botones que se usan en los tres ejemplos, para comparar cómo los
 * acomoda cada layout.
 */
final class Botones {

    private Botones() {
    }

    /** Crea botones nuevos cada vez: un componente Swing solo puede estar en un contenedor. */
    static List<JButton> ejemplo() {
        return List.of(
                new JButton("Button 1"),
                new JButton("Button 2"),
                new JButton("Button 3"),
                new JButton("Long-Named Button 4"),
                new JButton("Button 5"));
    }
}
