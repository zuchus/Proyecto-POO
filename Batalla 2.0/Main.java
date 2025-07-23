import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InterInicial inter = new InterInicial();
        inter.mostrarMenuPrincipal();
        Scanner sc = new Scanner(System.in);
        String nombre = inter.getNombreJugador();
        int tamaño = inter.getTamañoTablero();
        boolean ubiManual = inter.isUbicacionManual();
        int dificultad = inter.getDificultad();
        JugadorPC PC;
        switch (dificultad) {
            case 1:
                PC = new JugadorPC(tamaño); break;
            case 2:
                PC = new JugadorPCIntermedio(tamaño); break;
            case 3:
                PC = new JugadorPCDificil(tamaño); break;
            case 4:
                PC = new JugadorPCHacker(tamaño); break;
            default:
                PC = new JugadorPCIntermedio(tamaño); break;
        }
        Jugador Humano = new JugadorHumano(nombre, tamaño, sc);
        Juego juego = new Juego(Humano, PC, ubiManual);
        InterfazJuego ju = new InterfazJuego(juego, tamaño);
        
        if(ubiManual == true){
            juego.getPC().colocarBarcosAutomaticamente();
            ju.mostrarColocacionBarcos(juego.getHumano().getTablero());
            
        }else{
            juego.getHumano().colocarBarcosAutomaticamente();
            juego.getPC().colocarBarcosAutomaticamente();
            ju.crearVentana(Humano);
        }
        
        
    }  
    
}