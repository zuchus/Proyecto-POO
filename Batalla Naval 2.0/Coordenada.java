public class Coordenada{
    private int fila;
    private int columna;
    
    public Coordenada(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }
        
    public int getFila(){
        return fila;
    }
        
    public int getColumna(){
        return columna;
    }
        
    public void setFila(int filaCambio) {
        fila = filaCambio;
    }
        
    public void setColumna(int columnaCambio) {
        columna = columnaCambio;
    }
}