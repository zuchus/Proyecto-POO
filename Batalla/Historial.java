import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historial {

    private static final String NOMBRE_ARCHIVO = "historial_partidas.txt";

    public static void guardarResultado(String nombreJugador, int turnos, int aciertos, double porcentaje, boolean gano) {
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
        System.out.println("\n--- Historial de Partidas ---");

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el historial. Puede que no exista aún.");
        }

        System.out.println("------------------------------\n");
    }
}

