public class Barco {
    private Coordenada[] posiciones;
    private int tamaño;
    private String nombre;
    private boolean abatido;
    private int impactos = 0;
    
    public Barco(int tamaño, String nombre) {
        this.posiciones = new Coordenada[tamaño];
        this.tamaño = tamaño;
        this.nombre = nombre;
        this.abatido = false;
    }

    public Coordenada[] getPosiciones() {
        return posiciones;
    }

    public int getTamaño(){
        return tamaño;
    }
        
    public String getNombre(){
        return nombre;
    }

    public void setPosiciones(Coordenada[] posiciones) {
        this.posiciones = posiciones;
    }

    public boolean estaAbatido() {
        return abatido;
    }

    public boolean fueImpactado(Coordenada disparo) {
    for (Coordenada pos : posiciones) {
        if (pos.getFila() == disparo.getFila() && pos.getColumna() == disparo.getColumna()) {
            return true;
        }
    }
    
    return false;
    }
    
    public void registrarImpacto(){
    impactos++;
        if (impactos >= tamaño) {
            abatido = true;
        }
    }
        
        
}