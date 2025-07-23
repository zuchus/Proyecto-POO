import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;

public class InterfazJuego{
    private Juego juego;
    private JFrame ventana;
    private JTextArea areaTexto;
    private JPanel panTabJugador;
    private JPanel panTabPC;
    private JButton[][] botonesPC; //botones para disparar al oponente
    private JButton[][] tabJug; //tablero del jugador como matriz
    private boolean turnoJugador  = true;
    public int tamaño;
    //constructor para tener el tamaño
    public  InterfazJuego(Juego juego, int tamaño){
        this.juego =juego;
        this.tamaño = juego.getHumano().tab; //me lo saqué del culo pero sirvió yeeeeepeeee
        
    }
    
    //método para acomodar los barcos
    public void mostrarColocacionBarcos(Tablero tabJugador) {
        JFrame ventana = new JFrame("Ubica tus barcos");
        ventana.setSize(600, 650);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        int tamaño = this.tamaño;
        JButton[][] botones = new JButton[tamaño][tamaño];

        Map<String, Integer> barcosRestantes = new LinkedHashMap<>();
        barcosRestantes.put("Portaaviones(5)", 5);
        barcosRestantes.put("Buque(4)", 4);
        barcosRestantes.put("Crucero(3)", 3);
        barcosRestantes.put("Submarino(3)", 3);
        barcosRestantes.put("Destructor(2)", 2);
        
        JComboBox<String> comboBarcos = new JComboBox<>(barcosRestantes.keySet().toArray(new String[0]));
        JComboBox<String> comboOrientacion = new JComboBox<>(new String[]{"Horizontal", "Vertical"});

            JPanel panelGrilla = new JPanel(new GridLayout(tamaño, tamaño));
    for (int fila = 0; fila < tamaño; fila++) {
        for (int col = 0; col < tamaño; col++) {
            JButton btn = new JButton();
            btn.setBackground(Color.CYAN);
            int f = fila, c = col;

            btn.addActionListener(e -> {
                String nombreBarco = (String) comboBarcos.getSelectedItem();
                int tam = barcosRestantes.get(nombreBarco);
                boolean horiz = comboOrientacion.getSelectedItem().equals("Horizontal");
                
                Barco bar = new Barco(tam, nombreBarco);

                boolean colocado = tabJugador.colocarBarco(bar, f, c, horiz);
                if (colocado) {
                    // pintar los botones
                    for (int i = 0; i < tam; i++) {
                        int ff = f + (horiz ? 0 : i);
                        int cc = c + (horiz ? i : 0);
                        botones[ff][cc].setBackground(Color.GRAY);
                    }
                    // remover el barco del selector
                    comboBarcos.removeItem(nombreBarco);
                    barcosRestantes.remove(nombreBarco);

                    if (comboBarcos.getItemCount() == 0) {
                        JOptionPane.showMessageDialog(ventana, "¡Todos los barcos han sido colocados!");
                    }
                } else {
                    JOptionPane.showMessageDialog(ventana, "No se puede colocar el barco ahí.");
                }
            });
            botones[fila][col] = btn;
            panelGrilla.add(btn);
        }
    }

    // Panel de controles
    JPanel panelControles = new JPanel(new GridLayout(3, 2));
    panelControles.add(new JLabel("Barco:"));
    panelControles.add(comboBarcos);
    panelControles.add(new JLabel("Orientación:"));
    panelControles.add(comboOrientacion);

    JButton confirmar = new JButton("Confirmar");
    confirmar.addActionListener(e -> {
        if (comboBarcos.getItemCount() == 0) {
            ventana.dispose(); 
            crearVentana(this.juego.getHumano());
            
        } else {
            JOptionPane.showMessageDialog(ventana, "Aún faltan barcos por colocar.");
        }
    });

    panelControles.add(new JLabel()); // espacio vacío
    panelControles.add(confirmar);

    // Agregar todo a la ventana
    ventana.add(panelGrilla, BorderLayout.CENTER);
    ventana.add(panelControles, BorderLayout.SOUTH);
    ventana.setVisible(true);

    }
    


    public void crearVentana(Jugador jugadorActual) {
        
    JFrame ventana = new JFrame("Batalla Naval");
    areaTexto = new JTextArea(5, 30);
    areaTexto.setEditable(false);
    JScrollPane scroll = new JScrollPane(areaTexto);
    ventana.setSize(1200, 700);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setLayout(new BorderLayout());
ventana.add(scroll, BorderLayout.SOUTH);

    JPanel panelTableros = new JPanel(new GridLayout(1, 2));

    Tablero tableroPropio = this.juego.getHumano().getTablero();
    Tablero tableroEnemigo = this.juego.getPC().getTablero();

    JPanel panelPropio = new JPanel(new GridLayout(tamaño, tamaño));
    JPanel panelEnemigo = new JPanel(new GridLayout(tamaño, tamaño));

    tabJug = new JButton[tamaño][tamaño];
    botonesPC = new JButton[tamaño][tamaño];

    // Construir tablero propio (no interactivo)
    for (int fila = 0; fila < tamaño; fila++) {
        for (int col = 0; col < tamaño; col++) {
            JButton btn = new JButton();
            char valor = tableroPropio.getMatriz()[fila][col];

            if (valor == 'B') {
                btn.setBackground(Color.GRAY);
            } else if (valor == 'X') {
                btn.setBackground(Color.RED);
            } else if (valor == 'O') {
                btn.setBackground(Color.WHITE);
            } else {
                btn.setBackground(Color.CYAN);
            }

            btn.setEnabled(false);
            tabJug[fila][col] = btn;
            panelPropio.add(btn);
        }
    }

    // Construir tablero enemigo (interactivo)
    for (int fila = 0; fila < tamaño; fila++) {
        for (int col = 0; col < tamaño; col++) {
            JButton btn = new JButton();
            char valor = tableroEnemigo.getMatriz()[fila][col];

            if (valor == 'X') {
                btn.setBackground(Color.RED);
                btn.setEnabled(false);
            } else if (valor == 'O') {
                btn.setBackground(Color.WHITE);
                btn.setEnabled(false);
            } else {
                btn.setBackground(Color.CYAN); // ocultar barcos enemigos
            }

            int f = fila;
            int c = col;

            btn.addActionListener(e -> {
                if (!turnoJugador) return; // Si no es su turno, no hace nada

                boolean acierto = tableroEnemigo.recibirDisparo(f, c);

                if (acierto) {
                    btn.setBackground(Color.RED);
                    areaTexto.append("¡Impacto en (" + f + ", " + c + ")!\n");
                    btn.setEnabled(false);

                if (tableroEnemigo.juegoTerminado()) {
                    areaTexto.append("🎉 Le diste\n");
                    deshabilitarTablero(botonesPC);
                    if (juego.getPC().getTablero().juegoTerminado()) {
                        areaTexto.append("🎉 ¡Ganaste!\n");
                        JOptionPane.showMessageDialog(ventana, "¡Felicidades! Hundiste todos los barcos enemigos 🥳");
                        deshabilitarTablero(botonesPC);
                        return;
                    }
                    return;
                    
                }

        // Repite turno: no cambia turnoJugador
        areaTexto.append("¡Te toca otra vez!\n");

    } else {
        btn.setBackground(Color.WHITE);
        areaTexto.append("Fallaste en (" + f + ", " + c + ").\n");
        btn.setEnabled(false);

        turnoJugador = false; // Cambia turno
        areaTexto.append("Turno del enemigo :(\n");
        ejecutarTurnoPC();

       
    }

    actualizarTableroPropio(tabJug, tableroPropio);
});

            botonesPC[fila][col] = btn;
            panelEnemigo.add(btn);
        }
    }

    // Agregar títulos
    JLabel lblPropio = new JLabel("Tu tablero", JLabel.CENTER);
    JLabel lblEnemigo = new JLabel("Tablero enemigo", JLabel.CENTER);
    JPanel panelEtiquetas = new JPanel(new GridLayout(1, 2));
    panelEtiquetas.add(lblPropio);
    panelEtiquetas.add(lblEnemigo);

    panelTableros.add(panelPropio);
    panelTableros.add(panelEnemigo);

    ventana.add(panelEtiquetas, BorderLayout.NORTH);
    ventana.add(panelTableros, BorderLayout.CENTER);
    ventana.setVisible(true);
}
private void actualizarTableroPropio(JButton[][] buttons, Tablero tablero) {
    for (int fila = 0; fila < tamaño; fila++) {
        for (int col = 0; col < tamaño; col++) {
            char celda = tablero.getMatriz()[fila][col];
            JButton btn = tabJug[fila][col];

            switch (celda) {
                case 'X' -> btn.setBackground(Color.RED);   // Impacto
                case 'O' -> btn.setBackground(Color.WHITE); // Fallo
                case 'B' -> btn.setBackground(Color.GRAY);  // Barco sin impacto
                case '~' -> btn.setBackground(Color.CYAN);  // Agua sin disparo
            }
        }
    }
}
public void deshabilitarTablero(JButton[][] tab){
    for(int i = 0; i < tab[0].length; i++){
        for(int j =0; j < tab.length; j++){
            JButton btn = new JButton();
            tab[i][j] = btn;
            btn.setEnabled(false);
        }
    }
}
private void ejecutarTurnoPC() {
    while (!turnoJugador) {
        Coordenada disparo = juego.getPC().atacar(); 

        int fila = disparo.getFila();
        int col = disparo.getColumna();

        boolean acierto = juego.getHumano().recibirAtaque(disparo); 

        if (acierto) {
            areaTexto.append("💥 La PC acertó en (" + fila + ", " + col + "). Repite turno.\n");
            actualizarTableroPropio(tabJug, juego.getHumano().getTablero());

            if (juego.getHumano().getTablero().juegoTerminado()) {
                areaTexto.append("😢 ¡Perdiste! La PC hundió todos tus barcos.\n");
                deshabilitarTablero(botonesPC);
                break;
            }

        } else {
            areaTexto.append("La PC falló en (" + fila + ", " + col + "). Tu turno.\n");
            actualizarTableroPropio(tabJug, juego.getHumano().getTablero());
            turnoJugador = true;
        }
    }
}
}
