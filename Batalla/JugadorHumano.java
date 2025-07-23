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
    
    public void colocarBarcosManualmente() {
    System.out.println("\n=== Ubicación manual de barcos ===");

    for (Barco barco : barcos) {
        boolean colocado = false;

        while (!colocado) {
            try {
                System.out.println("\nColocando el barco: " + barco.getNombre() + " (tamaño: " + barco.getTamaño() + ")");

                System.out.print("Fila inicial (0 - " + (tablero.getTamaño() - 1) + "): ");
                int filaInicial = Integer.parseInt(sc.nextLine());

                System.out.print("Columna inicial (0 - " + (tablero.getTamaño() - 1) + "): ");
                int columnaInicial = Integer.parseInt(sc.nextLine());

                System.out.print("Orientación (H para horizontal, V para vertical): ");
                String orientacion = sc.nextLine().trim().toUpperCase();

                boolean horizontal;
                if (orientacion.equals("H")) {
                    horizontal = true;
                } else if (orientacion.equals("V")) {
                    horizontal = false;
                } else {
                    System.out.println("⚠️  Opción inválida. Escribe 'H' o 'V'.");
                    continue;
                }

                // Verificar que no se salga del tablero
                if ((horizontal && columnaInicial + barco.getTamaño() > tablero.getTamaño()) ||
                    (!horizontal && filaInicial + barco.getTamaño() > tablero.getTamaño())) {
                    System.out.println("🚫 El barco no cabe en esa posición. Intenta de nuevo.");
                    continue;
                }

                Coordenada[] posiciones = new Coordenada[barco.getTamaño()];
                for (int i = 0; i < barco.getTamaño(); i++) {
                    int fila = horizontal ? filaInicial : filaInicial + i;
                    int columna = horizontal ? columnaInicial + i : columnaInicial;
                    posiciones[i] = new Coordenada(fila, columna);
                }

                barco.setPosiciones(posiciones);

                if (tablero.colocarBarco(barco)) {
                    colocado = true;
                    tablero.mostrar(); // Mostrar el tablero tras cada colocación
                } else {
                    System.out.println("🚫 Ya hay un barco en esa posición. Intenta otra ubicación.");
                }

            } catch (NumberFormatException e) {
                System.out.println("🚫 Entrada inválida. Asegúrate de ingresar números.");
            }
        }
    }
    }
    
    public void mostrarTableroEnemigo(Tablero tableroEnemigo) {
        System.out.println("\n====== Tablero enemigo ====== \n");
    
        int tamaño = tableroEnemigo.getTamaño();
        int anchoCelda = 3;

        // Encabezado de columnas
        System.out.print(" ".repeat(anchoCelda));
        for (int col = 0; col < tamaño; col++) {
            System.out.printf("%" + anchoCelda + "d", col);
        }
        System.out.println();

        // Filas con índice y contenido
        for (int fila = 0; fila < tamaño; fila++) {
            System.out.printf("%" + anchoCelda + "d", fila);

            for (int col = 0; col < tamaño; col++) {
                String clave = fila + "," + col;
                char simbolo = '~'; // Agua no disparada por defecto

                if (disparosRealizados.contains(clave)) {
                    char celdaReal = tableroEnemigo.getCelda(fila, col);

                    if (celdaReal == 'X') {
                        simbolo = 'X'; // Impacto
                    } else if (celdaReal == 'O') {
                        simbolo = 'O'; // Fallo
                    }
                }

                System.out.printf("%" + anchoCelda + "s", simbolo);
            }
            System.out.println();
        }
    }
    
}