import java.util.*;

public class JugadorPCHacker extends JugadorPC {
    private List<Coordenada> objetivos;
    private int siguienteObjetivo = 0;

    public JugadorPCHacker(int tamañoTablero) {
        super(tamañoTablero);
        objetivos = new ArrayList<>();
    }

    public void analizarBarcos(Jugador jugadorHumano) {
        // Obtener todos los barcos y registrar sus coordenadas
        for (Barco b : jugadorHumano.getBarcos()) {
            objetivos.addAll(Arrays.asList(b.getPosiciones()));
        }
    }

    @Override
    public Coordenada atacar() {
        if (siguienteObjetivo < objetivos.size()) {
            Coordenada objetivo = objetivos.get(siguienteObjetivo);
            siguienteObjetivo++;
            marcarDisparo(objetivo);
            System.out.println("💀 CPU ataca en " + objetivo.getFila() + "," + objetivo.getColumna());
            return objetivo;
        } else {
            // Por si se agotan (no debería pasar)
            return super.atacar();
        }
    }
}