public abstract class Barco {
    protected Coordenada[] posiciones;
    protected int tamaño;
    protected boolean abatido;
    protected int impactos = 0;
    
    public Barco(int tamaño) {
        this.tamaño = tamaño;
        this.abatido = false;
        this.posiciones = new Coordenada[tamaño];
    }

    public int getTamaño(){
        return tamaño;}

    public Coordenada[] getPosiciones() {
        return posiciones;}

    public void setPosiciones(Coordenada[] posiciones) {
        this.posiciones = posiciones;}

    public boolean estaAbatido() {
        return abatido;}

    public void marcarAbatido() {
        this.abatido = true;
    }
    
    public boolean fueImpactado(Coordenada disparo) {
    for (Coordenada pos : posiciones) {
        if (pos.getFila() == disparo.getFila() && pos.getColumna() == disparo.getColumna()) {
            return true;}
    }
    return false;}
    
    public void registrarImpacto(){
    impactos++;
    if (impactos >= tamaño) {
        abatido = true;}}
        
        
}