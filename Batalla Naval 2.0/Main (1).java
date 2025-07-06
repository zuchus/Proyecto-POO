import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (true) {
            opcion = mostrarMenu(sc);

            if (opcion == 1) {
                crearJuego(sc);
            } else if (opcion == 2) {
                mostrarInstrucciones();
            } else if (opcion == 3) {
                System.out.println("¡Hasta luego!");
                break;
            }
        }
    }

    public static int mostrarMenu(Scanner sc) {
        int opcion = 0;

        while (true) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Jugar");
            System.out.println("2. Ver instrucciones");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion >= 1 && opcion <= 3) {
                    return opcion;
                } else {
                    System.out.println("Por favor, elige una opción válida (1-3).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }

    public static void mostrarInstrucciones() {
        System.out.println("\n=== Instrucciones ===");
        System.out.println("El objetivo del juego es hundir todos los barcos del oponente.");
        System.out.println("Cada jugador dispone de 5 barcos de diferentes tamaños.");
        System.out.println("Durante cada turno, deberás ingresar coordenadas para atacar.");
        System.out.println("¡El primer jugador en hundir todos los barcos enemigos gana!\n");
    }

    public static void crearJuego(Scanner sc) {
        System.out.print("\nIngresa tu nombre: ");
        String nombre = sc.nextLine();

        int tamaño = 0;
        while (tamaño < 5 || tamaño > 15) {
            System.out.print("Selecciona el tamaño del tablero (5 a 15): ");
            try {
                tamaño = Integer.parseInt(sc.nextLine());
                if (tamaño < 5 || tamaño > 15) {
                    System.out.println("Debe ser un número entre 5 y 15.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }

        Jugador Humano = new JugadorHumano(nombre, tamaño, sc);
        Jugador PC = new JugadorPC(tamaño);

        new Juego(Humano, PC, sc); 
    }
}