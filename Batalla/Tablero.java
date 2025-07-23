public class Tablero {
    private char[][] matriz;
    private int tamaño;

    public Tablero(int tamaño) {
        this.tamaño = tamaño;
        this.matriz = new char[tamaño][tamaño];
        inicializar();
    }
    
    public int getTamaño(){
        return tamaño;
    }
    
    public char getCelda(int fila, int columna) {
    return matriz[fila][columna];
    }
    
    private void inicializar() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                matriz[i][j] = '~'; // '~' representa agua
            }
        }
    }

    public boolean estaEnRango(Coordenada coord) {
        int fila = coord.getFila();
        int columna = coord.getColumna();
        return fila >= 0 && fila < tamaño && columna >= 0 && columna < tamaño;
    }

    public boolean estaDisponible(Coordenada coord) {
        return estaEnRango(coord) && matriz[coord.getFila()][coord.getColumna()] == '~';
    }

    public boolean colocarBarco(Barco barco) {
        
        for (Coordenada pos : barco.getPosiciones()) {
            if (!estaDisponible(pos)) {
                return false;
            }    
        }
        
        for (Coordenada pos : barco.getPosiciones()) {
            matriz[pos.getFila()][pos.getColumna()] = 'B';
        }
        
        return true;
        
    }

    public boolean recibirDisparo(Coordenada disparo, Barco[] barcos) {
        for (Barco b : barcos) {
            if (b.fueImpactado(disparo)) {
                b.registrarImpacto();
                matriz[disparo.getFila()][disparo.getColumna()] = 'X'; // 'X' es impacto
                return true;
            }    
        }    
        matriz[disparo.getFila()][disparo.getColumna()] = 'O'; // 'O' es fallo
        return false;
    }

    public void mostrar() {
        // Tamaño fijo de celda
        int anchoCelda = 3;

        // Encabezado de columnas
        System.out.print(" ".repeat(anchoCelda));
        for (int col = 0; col < tamaño; col++) {
            System.out.printf("%" + anchoCelda + "d", col);
        }
        System.out.println();

        // Filas con índice + contenido
        for (int fila = 0; fila < tamaño; fila++) {
            System.out.printf("%" + anchoCelda + "d", fila);
            for (int col = 0; col < tamaño; col++) {
                System.out.printf("%" + anchoCelda + "s", matriz[fila][col]);
            }
            System.out.println();
        }
    }
    
}