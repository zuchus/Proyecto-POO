import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private char[][] matriz;
    private int tamaño;
    private List<Barco> barcos = new ArrayList<>();

    public Tablero(int tamaño) {
        this.tamaño = tamaño;
        this.matriz = new char[tamaño][tamaño];
        inicializar();
    }
    
    public int getTamaño(){
        return tamaño;
    }
    public char[][] getMatriz(){
        return matriz;
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

    public boolean colocarBarco(Barco barco, int fila, int columna, boolean horizontal) {
    int tamaño = barco.getTamaño(); // Asumo que Barco tiene un método así

    // Verifica si el barco se sale del tablero
    if (horizontal) {
        if (columna + tamaño > matriz[0].length) return false;
    } else {
        if (fila + tamaño > matriz.length) return false;
    }

    // Verifica si se superpone con otro barco
    for (int i = 0; i < tamaño; i++) {
        int f = fila + (horizontal ? 0 : i);
        int c = columna + (horizontal ? i : 0);
        if (matriz[f][c] != '~') { // o el valor que uses para indicar agua vacía
            return false;
        }
    }

    // Colocar el barco
    for (int i = 0; i < tamaño; i++) {
        int f = fila + (horizontal ? 0 : i);
        int c = columna + (horizontal ? i : 0);
        matriz[f][c] = 'B'; // o el símbolo que quieras
        barco.agregarCoordenada(new Coordenada(f, c)); // si estás rastreando coordenadas del barco
    }

    barcos.add(barco);
    return true;
}

    public boolean recibirDisparo(Coordenada disparo) {
    for (Barco b : this.barcos) {
        if (b.fueImpactado(disparo)) {
            b.registrarImpacto();
            matriz[disparo.getFila()][disparo.getColumna()] = 'X';
            return true;
        }
        }

        matriz[disparo.getFila()][disparo.getColumna()] = 'O';
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
    public boolean colocarBarco(Barco barco) {
    for (Coordenada pos : barco.getPosiciones()) {
        if (!estaDisponible(pos)) {
            return false;
        }
    }

    for (Coordenada pos : barco.getPosiciones()) {
        matriz[pos.getFila()][pos.getColumna()] = 'B';
        barco.agregarCoordenada(pos); // ← importante
    }

    barcos.add(barco); // ← importante también

    return true;
}
    public boolean recibirDisparo(int fila, int columna) {
    char celda = matriz[fila][columna];

    if (celda == 'B') {
        matriz[fila][columna] = 'X'; // impacto
        return true;
    } else if (celda == '~') {
        matriz[fila][columna] = 'O'; // fallo
        return false;
    } else {
        // ya se disparó aquí antes (X u O), no hacemos nada
        return false;
    }
}
public boolean juegoTerminado() {
    for (Barco barco : barcos) {
        for (Coordenada coord : barco.getCoordenadas()) {
            if (coord == null) continue; // 🔐 Evita nulls

            int fila = coord.getFila();
            int col = coord.getColumna();

            if (matriz[fila][col] != 'X') {
                return false; // Aún queda parte viva
            }
        }
    }
    return true;
}
    
}