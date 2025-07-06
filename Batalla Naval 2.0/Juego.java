import java.util.Scanner;

public class Juego {
    private Jugador jugadorHumano;
    private Jugador jugadorPC;
    private Scanner sc;

    public Juego(Jugador Humano, Jugador PC, Scanner sc) {
        this.jugadorHumano = Humano;
        this.jugadorPC = PC;
        this.sc = sc;
        iniciarJuego();
    }

    private void iniciarJuego() {
        jugadorHumano.colocarBarcosAutomaticamente();
        
        System.out.println("Así quedaron colocados tus barcos:");
        jugadorHumano.getTablero().mostrar();

        jugadorPC.colocarBarcosAutomaticamente();

        System.out.println("\n¡Comienza la partida!");

        while (!jugadorHumano.todosAbatidos() && !jugadorPC.todosAbatidos()) {
            System.out.println("\nTurno de " + jugadorHumano.getNombre());
            Coordenada disparoHumano = jugadorHumano.atacar();
            jugadorPC.recibirAtaque(disparoHumano);
            
            // Mostrar cómo va quedando el tablero del humano
            System.out.println("\nEstado actual:");
            jugadorHumano.getTablero().mostrar();
            
            if (jugadorPC.todosAbatidos()) break;

            System.out.println("\nTurno de la computadora:");
            Coordenada disparoPC = jugadorPC.atacar();
            jugadorHumano.recibirAtaque(disparoPC);
            
            // Mostrar cómo va quedando el tablero del humano
            System.out.println("\nEstado actual:");
            jugadorHumano.getTablero().mostrar();
        }

        if (jugadorHumano.todosAbatidos()) {
            System.out.println("La computadora ha ganado.");
        } else {
            System.out.println("¡" + jugadorHumano.getNombre() + " ha ganado!");
        }
    }
}