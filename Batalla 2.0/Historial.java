import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historial {

    private static final String NOMBRE_ARCHIVO = "historial_partidas.txt";

    public static void guardarResultado(String nombreJugador, int turnos, int aciertos, double porcentaje,
            boolean gano) {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO, true)) {
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            writer.write("Fecha: " + ahora.format(formato) + "\n");
            writer.write("Jugador: " + nombreJugador + "\n");
            writer.write("Resultado: " + (gano ? "Ganó" : "Perdió") + "\n");
            writer.write("Turnos: " + turnos + "\n");
            writer.write("Impactos: " + aciertos + "\n");
            writer.write(String.format("Porcentaje de aciertos: %.2f%%\n", porcentaje));
            writer.write("---------------------------------------\n");

        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    public static void mostrarHistorial() {
        JDialog ventana = new JDialog();
        ventana.setTitle("Historial de Partidas");
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new BorderLayout());

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 13));

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                areaTexto.append(linea + "\n");
            }
        } catch (IOException e) {
            areaTexto.setText("No se pudo leer el historial. Puede que no exista aún.");
        }

        JScrollPane scroll = new JScrollPane(areaTexto);
        ventana.add(scroll, BorderLayout.CENTER);

        JButton botonVolver = new JButton("Volver al menú principal");
        botonVolver.addActionListener(e -> {
            ventana.dispose();
            new InterInicial().mostrarMenuPrincipal();
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonVolver);
        ventana.add(panelBoton, BorderLayout.SOUTH);

        ventana.setVisible(true);
    }
}
