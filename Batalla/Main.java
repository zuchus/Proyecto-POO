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
                Historial.mostrarHistorial(); 
            } else if (opcion == 3) {
                mostrarInstrucciones();
            } else if (opcion == 4) {
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
            System.out.println("2. Ver historial");         
            System.out.println("3. Ver instrucciones");
            System.out.println("4. Salir");                  
            System.out.println("======================");
            System.out.print("\nElige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion >= 1 && opcion <= 4) {
                    return opcion;
                } else {
                    System.out.println("Por favor, elige una opción válida (1-4).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }

    public static void mostrarInstrucciones() {
        System.out.println("\n======================= Instrucciones =======================");
        System.out.println("El objetivo del juego es hundir todos los barcos del oponente.");
        System.out.println("Cada jugador dispone de 5 barcos de diferentes tamaños.");
        System.out.println("1. Portaaviones (5)");
        System.out.println("2. Buque (4)");
        System.out.println("3. Crucero (3)");
        System.out.println("4. Submarino (3)");
        System.out.println("5. Destructor (2)");
        System.out.println("Durante cada turno, deberás ingresar coordenadas para atacar.");
        System.out.println("¡El primer jugador en hundir todos los barcos enemigos gana!");
        System.out.println("==============================================================\n");
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

        boolean ubicacionManual = false;
        while (true) {
            System.out.print("¿Quieres colocar los barcos manualmente? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            if (respuesta.equals("s")) {
                ubicacionManual = true;
                break;
            } else if (respuesta.equals("n")) {
                break;
            } else {
                System.out.println("Respuesta inválida. Escribe 's' o 'n'.");
            }
        }

        System.out.println("\nSelecciona la dificultad:");
        System.out.println("1) Principiante");
        System.out.println("2) Intermedio");
        System.out.println("3) Difícil");
        System.out.println("4) Hacker");

        int dificultad = 1;
        while (true) {
            try {
                System.out.print("Opción (1-4): ");
                dificultad = Integer.parseInt(sc.nextLine());
                if (dificultad >= 1 && dificultad <= 4) break;
                else System.out.println("Por favor ingresa un número entre 1 y 4.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }

        Jugador Humano = new JugadorHumano(nombre, tamaño, sc);
        Jugador PC;

        switch (dificultad) {
            case 1:
                PC = new JugadorPC(tamaño); break;
            case 2:
                PC = new JugadorPCIntermedio(tamaño); break;
            case 3:
                PC = new JugadorPCDificil(tamaño); break;
            case 4:
                PC = new JugadorPCHacker(tamaño); break;
            default:
                PC = new JugadorPC(tamaño);
        }

        new Juego(Humano, PC, ubicacionManual);
    }
}