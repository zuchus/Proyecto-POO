public class Tablero {
    private int filas;
    private int columnas;
    private char[][] matriz;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new char[filas][columnas];
        inicializar();}

    public void inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = '-'; // símbolo vacío
            }
        }
    }

    public boolean colocarBarco(Barco barco, Coordenada inicio, boolean horizontal) {
        Coordenada[] posiciones = new Coordenada[barco.getTamaño()];

        int fila = inicio.getFila();
        int columna = inicio.getColumna();

        // Validar que el barco quepa y no se solape
        for (int i = 0; i < barco.getTamaño(); i++) {
            int f = horizontal ? fila : fila + i;
            int c = horizontal ? columna + i : columna;

            if (!estaEnRango(f, c) || matriz[f][c] != '-') {
                return false; // No se puede colocar
            }

            posiciones[i] = new Coordenada(f, c);
        }

        // Colocar barco en matriz y registrar coordenadas
        for (Coordenada pos : posiciones) {
            matriz[pos.getFila()][pos.getColumna()] = 'B';
        }

        barco.setPosiciones(posiciones);
        return true;
    }

    public boolean recibirDisparo(Coordenada c) {
        int fila = c.getFila();
        int columna = c.getColumna();

        if (!estaEnRango(fila, columna)) return false;

        char actual = matriz[fila][columna];

        if (actual == 'B') {
            matriz[fila][columna] = 'X'; // Impacto
            return true;
        } else if (actual == '-') {
            matriz[fila][columna] = 'O'; // Agua
            return false;
        }

        return false; // Ya disparado
    }

    private boolean estaEnRango(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;}

    public void mostrar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMatriz() {
        return matriz;}
        
}