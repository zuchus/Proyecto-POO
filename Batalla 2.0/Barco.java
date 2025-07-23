import java.util.*;
public class Barco {
    private Coordenada[] posiciones;
    private int tamaño;
    private String nombre;
    private boolean abatido;
    private int impactos = 0;
    private List<Coordenada> coordenadas = new ArrayList<>();
    
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
        boolean a = false;
        for (Coordenada pos : posiciones) {
            if (pos != null &&
            pos.getFila() == disparo.getFila() &&
            pos.getColumna() == disparo.getColumna()) {
             a = true;
            }
    }return a;
    }
    
    public void registrarImpacto(){
        impactos++;
        if (impactos >= tamaño) {
            abatido = true;
        }
    }
    public void agregarCoordenada(Coordenada coord) {
        coordenadas.add(coord);
    }    
    public List<Coordenada> getCoordenadas() {
        return coordenadas;
    } 
}