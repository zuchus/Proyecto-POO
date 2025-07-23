import java.util.Scanner;

public class Juego {
    private Jugador jugadorHumano;
    private Jugador jugadorPC;
    
    public Juego(Jugador Humano, Jugador PC, boolean ubicacionManual) {
        this.jugadorHumano = Humano;
        this.jugadorPC = PC;
        //jugar(ubicacionManual);
    }

    private void jugar(boolean ubicacionManual) {
    int turnosHumano = 0;
    int impactosHumano = 0;

    // Colocación de barcos (manual o automática)

    jugadorPC.colocarBarcosAutomaticamente();
    
    if (jugadorPC instanceof JugadorPCHacker) {
    ((JugadorPCHacker) jugadorPC).analizarBarcos(jugadorHumano);
    }
    
    System.out.println("\n¡Comienza la partida!");

    // Bucle principal del juego
    while (!jugadorHumano.todosAbatidos() && !jugadorPC.todosAbatidos()) {
        // === Turno del humano ===
        System.out.println("\nTurno de " + jugadorHumano.getNombre().trim() +":");
        boolean aciertoHumano;

        do {
            turnosHumano++;
            Coordenada disparoHumano = jugadorHumano.atacar();
            aciertoHumano = jugadorPC.recibirAtaque(disparoHumano);

            if (aciertoHumano) impactosHumano++;
            System.out.println(aciertoHumano ? "✅ ¡Impacto!" : "❌  Agua.");
            // Mostrar tablero enemigo (solo marcas de disparos)
            ((JugadorHumano) jugadorHumano).mostrarTableroEnemigo(jugadorPC.getTablero());
            System.out.println("\n");

            // Notificar hundimiento
            Barco hundidoPC = jugadorPC.barcoHundidoEn(disparoHumano);
            if (hundidoPC != null) {
                System.out.println("\n💥 ¡Hundiste el " + hundidoPC.getNombre() + " enemigo!\n");
            }

        } while (aciertoHumano && !jugadorPC.todosAbatidos());

        // Si el humano ganó, salimos
        if (jugadorPC.todosAbatidos()) break;

        // === Turno de la computadora ===
        System.out.println("\nTurno de la computadora:");
        boolean aciertoPC;

        do {
            Coordenada disparoPC = jugadorPC.atacar();
            aciertoPC = jugadorHumano.recibirAtaque(disparoPC);
            
            if (jugadorPC instanceof JugadorPCIntermedio) {
            ((JugadorPCIntermedio) jugadorPC).registrarResultado(disparoPC, aciertoPC);
            }
            
            if (jugadorPC instanceof JugadorPCDificil) {
            ((JugadorPCDificil) jugadorPC).registrarResultado(disparoPC, aciertoPC);
            }
            
            
            System.out.println(aciertoPC ? "💣 ¡Impacto de la CPU!" : "💨  Fallo de la CPU.");

            // Notificar hundimiento
            Barco hundidoHumano = jugadorHumano.barcoHundidoEn(disparoPC);
            if (hundidoHumano != null) {
                System.out.println("\n☠️  ¡La CPU hundió tu " + hundidoHumano.getNombre() + "!\n");
            }

            // Mostrar tablero propio
            System.out.println("\n====== Tu tablero ====== \n");
            jugadorHumano.getTablero().mostrar();
            System.out.println("\n");
            
        } while (aciertoPC && !jugadorHumano.todosAbatidos());
    }

    // === Resultado final ===
    System.out.println("\n-------- Resultado Final --------");
    if (jugadorHumano.todosAbatidos()) {
        System.out.println("🧠  La computadora ha ganado.");
    } else {
        System.out.println("🏆  ¡" + jugadorHumano.getNombre().trim() + " ha ganado!");
    }

    // === Estadísticas ===
    double porcentaje = (turnosHumano > 0) ? ((double) impactosHumano / turnosHumano) * 100 : 0;

    System.out.println("\n--------- Estadísticas ----------");
    System.out.println("📊  Turnos del jugador: " + turnosHumano);
    System.out.println("🎯  Impactos acertados: " + impactosHumano);
    System.out.printf("📈  Porcentaje de aciertos: %.2f%%\n", porcentaje);
    System.out.println("---------------------------------");
    
    // === Guardar en historial ===
    boolean gano = !jugadorHumano.todosAbatidos();
    Historial.guardarResultado(
        jugadorHumano.getNombre(),
        turnosHumano,
        impactosHumano,
        porcentaje,
        gano
    );
        
    }
    public Jugador getHumano(){
        return this.jugadorHumano;
    }
    public Jugador getPC(){
        return this.jugadorPC;
    }
}