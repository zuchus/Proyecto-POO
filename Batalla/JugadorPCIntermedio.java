import java.util.ArrayList;

public class JugadorPCIntermedio extends JugadorPC {
    private Coordenada ultimoImpacto = null;
    private ArrayList<Coordenada> candidatos = new ArrayList<>();

    public JugadorPCIntermedio(int tamañoTablero) {
        super(tamañoTablero);
    }

    @Override
    public Coordenada atacar() {
        Coordenada disparo;

        // Si hay coordenadas candidatas (vecinas de un impacto anterior), las probamos primero
        while (!candidatos.isEmpty()) {
            disparo = candidatos.remove(0);
            if (!yaDisparo(disparo)) {
                marcarDisparo(disparo);
                System.out.println("CPU ataca en (" + disparo.getFila() + ", " + disparo.getColumna() + ")");
                return disparo;
            }
        }

        // Si no hay candidatos válidos, atacamos como el PC básico
        disparo = super.atacar(); // Usa el método ya implementado en JugadorPC
        return disparo;
    }

    public void registrarResultado(Coordenada disparo, boolean acierto) {
        if (acierto) {
            this.ultimoImpacto = disparo;
            generarVecinos(disparo);
        }
    }

    private void generarVecinos(Coordenada centro) {
        int fila = centro.getFila();
        int col = centro.getColumna();
        int tamaño = tablero.getTamaño();

        // Vecinos ortogonales: arriba, abajo, izquierda, derecha
        if (fila > 0) candidatos.add(new Coordenada(fila - 1, col));
        if (fila < tamaño - 1) candidatos.add(new Coordenada(fila + 1, col));
        if (col > 0) candidatos.add(new Coordenada(fila, col - 1));
        if (col < tamaño - 1) candidatos.add(new Coordenada(fila, col + 1));
    }
}