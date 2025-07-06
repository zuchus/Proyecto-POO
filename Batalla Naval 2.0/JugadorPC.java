import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JugadorPC extends Jugador {

    private Random random;
    private Set<String> disparosRealizados;

    public JugadorPC(int tamañoTablero) {
        super("CPU", tamañoTablero);
        random = new Random();
        disparosRealizados = new HashSet<>();
    }

    @Override
    public Coordenada atacar() {
        int fila, columna;
        String clave;

        do {
            fila = random.nextInt(tablero.getTamaño());
            columna = random.nextInt(tablero.getTamaño());
            clave = fila + "," + columna;
        } while (disparosRealizados.contains(clave));

        disparosRealizados.add(clave);

        System.out.println("CPU ataca en (" + fila + ", " + columna + ")");
        return new Coordenada(fila, columna);
    }

    
    
}