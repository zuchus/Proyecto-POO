import java.util.*;

public class JugadorPCDificil extends JugadorPC {
    private Coordenada ultimoImpacto = null;
    private boolean modoCaza = false;
    private Coordenada origenCaza = null;
    private String direccionActual = null;
    private List<String> direccionesPendientes = new ArrayList<>();

    public JugadorPCDificil(int tamañoTablero) {
        super(tamañoTablero);
    }

    @Override
    public Coordenada atacar() {
        if (modoCaza && direccionActual != null) {
            // Continuar en línea recta
            System.out.println("ataque jodo");
            Coordenada siguiente = obtenerSiguienteEnDireccion(ultimoImpacto, direccionActual);
            if (siguiente != null && !yaDisparo(siguiente)) {
                marcarDisparo(siguiente);
                System.out.println("CPU sigue en dirección " + direccionActual + " hacia " + formatear(siguiente));
                return siguiente;
            } else {
                // Cambiar de dirección
                direccionActual = null;
            }
        }

        if (modoCaza) {
            // Probar otras direcciones desde origen
            while (!direccionesPendientes.isEmpty()) {
                String dir = direccionesPendientes.remove(0);
                Coordenada vecino = obtenerSiguienteEnDireccion(origenCaza, dir);
                if (vecino != null && !yaDisparo(vecino)) {
                    direccionActual = dir;
                    marcarDisparo(vecino);
                    System.out.println("CPU explora " + dir + " desde " + formatear(origenCaza));
                    return vecino;
                }
            }

            // Si agotó direcciones
            modoCaza = false;
            direccionActual = null;
            origenCaza = null;
        }

        // Disparo aleatorio
        Coordenada aleatorio = super.atacar();
        return aleatorio;
    }

    public void registrarResultado(Coordenada c, boolean acierto) {
        if (acierto) {
            if (!modoCaza) {
                modoCaza = true;
                origenCaza = c;
                direccionesPendientes = new ArrayList<>(Arrays.asList("arriba", "abajo", "izquierda", "derecha"));
                Collections.shuffle(direccionesPendientes);
            }
            ultimoImpacto = c;
        } else if (modoCaza) {
            // falló, cambiar dirección si está en seguimiento
            direccionActual = null;
        }
    }

    private Coordenada obtenerSiguienteEnDireccion(Coordenada base, String direccion) {
        int f = base.getFila();
        int c = base.getColumna();
        int tamaño = getTablero().getTamaño();

        switch (direccion) {
            case "arriba":
                return (f > 0) ? new Coordenada(f - 1, c) : null;
            case "abajo":
                return (f < tamaño - 1) ? new Coordenada(f + 1, c) : null;
            case "izquierda":
                return (c > 0) ? new Coordenada(f, c - 1) : null;
            case "derecha":
                return (c < tamaño - 1) ? new Coordenada(f, c + 1) : null;
            default:
                return null;
        }
    }

    private String formatear(Coordenada c) {
        return "(" + c.getFila() + "," + c.getColumna() + ")";
    }
}