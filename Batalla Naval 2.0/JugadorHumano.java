import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.InputMismatchException;

public class JugadorHumano extends Jugador {
    private Scanner sc;
    private Set<String> disparosRealizados = new HashSet<>();

    public JugadorHumano(String nombre, int tamañoTablero, Scanner sc) {
        super(nombre, tamañoTablero);
        this.sc = sc;
    }

    @Override
    public Coordenada atacar() {
        int tamaño = tablero.getTamaño();  
        while (true) {
            try {
                System.out.print("Ingresa fila del disparo: ");
                int fila = sc.nextInt();

                System.out.print("Ingresa columna del disparo: ");
                int columna = sc.nextInt();
                sc.nextLine(); 

                // Verificación de rango
                if (fila < 0 || fila >= tamaño || columna < 0 || columna >= tamaño) {
                    System.out.println("🚫  Coordenadas fuera de los límites del mapa. Intenta de nuevo.");
                    continue;
                }

                String clave = fila + "," + columna;
                if (disparosRealizados.contains(clave)) {
                    System.out.println("¡Ya disparaste ahí! ¿Te gusta rematarlos? 😅");
                    continue;
                }

                disparosRealizados.add(clave);
                return new Coordenada(fila, columna);

            } catch (InputMismatchException e) {
                System.out.println("🚫  Entrada inválida. Solo se permiten números. Intenta de nuevo.");
                sc.nextLine(); // Limpia la entrada inválida
            }
        }
    }

    
}