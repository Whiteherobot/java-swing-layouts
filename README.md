# java-swing-layouts

[![CI](https://github.com/Whiteherobot/java-swing-layouts/actions/workflows/ci.yml/badge.svg)](https://github.com/Whiteherobot/java-swing-layouts/actions/workflows/ci.yml)

Ejercicio 03.01 de Programación Orientada a Objetos (mayo de 2022), en grupo:
ventanas y paneles con Swing. Una ventana con pestañas muestra cómo acomodan
los mismos cinco botones tres layouts distintos, y la última pestaña arma la
bandera de Noruega con un `GridLayout`.

![Las cuatro pestañas](docs/captura.png)

- **BorderLayout**: cinco zonas; el centro se queda con el espacio libre.
- **GridLayout**: celdas iguales, dos columnas.
- **FlowLayout**: en fila con su tamaño natural, y salta de línea cuando no cabe.
- **Bandera**: 16 × 22 celdas, las proporciones oficiales de la bandera de
  Noruega. El color de cada celda se calcula a partir de la posición de la cruz,
  y la bandera mantiene su forma aunque cambie el tamaño de la ventana.

## Cómo correrlo

Necesitas JDK 17 o superior. El proyecto trae el wrapper de Maven.

```bash
./mvnw verify                             # compila y corre las pruebas
java -jar target/swing-layouts-1.0.0.jar
```

## Qué cambió desde la entrega

En 2022 eran cuatro ventanas separadas que se cerraban todas juntas, con
etiquetas (`JLabel`) que hacían de botones y tres clases casi idénticas. La
bandera se pintaba celda por celda en 120 líneas y tenía dos filas rojas arriba
y una abajo, así que salía deformada. En 2026 quedó una sola ventana con
pestañas, botones reales, un panel por layout, la bandera generada con sus
proporciones y la interfaz creada en el hilo de eventos de Swing.

## Autores

- Michael Lata ([@Whiteherobot](https://github.com/Whiteherobot)): layouts
- Gabriela Rocano: bandera

Licencia MIT.
