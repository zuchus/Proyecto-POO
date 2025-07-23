import java.util.Random;

public abstract class Jugador {
    protected String nombre;
    protected Barco[] barcos;
    protected Tablero tablero;

    public Jugador(String nombre, int tamañoTablero) {
        this.nombre = nombre;
        this.barcos = new Barco[5];  
        this.barcos[0] = new Barco(5, "Portaaviones");
        this.barcos[1] = new Barco(4, "Buque");
        this.barcos[2] = new Barco(3, "Crucero");
        this.barcos[3] = new Barco(3, "Submarino");
        this.barcos[4] = new Barco(2, "Destructor");
        this.tablero = new Tablero(tamañoTablero);  // El jugador tiene su propio tablero
    }

    public String getNombre() {
        return nombre;}

    public Tablero getTablero() {
        return tablero;}

    public Barco[] getBarcos() {
        return barcos;}

    public void setBarcos(Barco[] barcos) {
        this.barcos = barcos;}

    // Método para ubicar barcos de forma automática
    public void colocarBarcosAutomaticamente() {
        
    Random random = new Random();

        for (Barco barco : barcos) {
            boolean colocado = false;

            while (!colocado) {
                boolean horizontal = random.nextBoolean();

                int maxFila = tablero.getTamaño();
                int maxColumna = tablero.getTamaño();

                int filaInicial = horizontal
                    ? random.nextInt(maxFila)
                    : random.nextInt(maxFila - barco.getTamaño() + 1);

                int columnaInicial = horizontal
                    ? random.nextInt(maxColumna - barco.getTamaño() + 1)
                    : random.nextInt(maxColumna);

                Coordenada[] posiciones = new Coordenada[barco.getTamaño()];

                for (int i = 0; i < barco.getTamaño(); i++) {
                    int fila = horizontal ? filaInicial : filaInicial + i;
                    int columna = horizontal ? columnaInicial + i : columnaInicial;
                    posiciones[i] = new Coordenada(fila, columna);
                }

                barco.setPosiciones(posiciones);

                // Validar si puede colocarse y colocarlo
                if (tablero.colocarBarco(barco)) {
                colocado = true;
                }
            }
        }
        
    }

    // Método abstracto para atacar (diferente lógica entre humano y PC)
    public abstract Coordenada atacar();

    // Método para actualizar el tablero propio con disparos recibidos
    public boolean recibirAtaque(Coordenada disparo) {
        return tablero.recibirDisparo(disparo, barcos);
    }
    
    public boolean todosAbatidos() {
        for (Barco b : barcos) {
            if (!b.estaAbatido()) return false;
        }    
        return true;
    }
        
    public Barco barcoHundidoEn(Coordenada disparo) {
        for (Barco b : barcos) {
            if (b.fueImpactado(disparo) && b.estaAbatido()) {
                return b;
            }
        }
    return null;
    }
        
}