import javax.swing.*;
import java.awt.*;

public class InterInicial {
    private String nombreJugador;
    private int tamañoTab;
    private boolean ubicacionMan;
    private int dificultad;

    // creamos método pa mostrar el menú
    public void mostrarMenuPrincipal() {

        String[] opciones = { "Jugar", "Ver historial", "Ver instrucciones", "Salir" };

        // Esta línea muestra una ventanita con botones
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "=== Menú Principal ===",
                "Batalla Naval",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        // Qué eligió el usuario?
        switch (eleccion) {
            case 0: // Si eligió "Jugar"
                recogerDatos(); // Creamos la interfaz del juego
                break;
            case 1:
                Historial.mostrarHistorial();
                break;
            case 2: // Si eligió "Ver instrucciones"
                mostrarInstruccionesI(); // Llama a otro método que te explico abajo

                break;
            case 3: // Si eligió "Salir"
            case JOptionPane.CLOSED_OPTION: // O si cerró la ventana con la X
                System.exit(0); // Cerramos el programa
                break;
        }

    }

    public void mostrarInstruccionesI() {
        JDialog inst = new JDialog();
        inst.setTitle("Instrucciones");
        inst.setSize(600, 500);
        inst.setLocationRelativeTo(null);
        inst.setLayout(new BorderLayout());

        JTextArea instrucciones = new JTextArea("""
                🌊 INSTRUCCIONES 🌊

                - El objetivo del juegp es hundir todos los barcos del enemigo.
                - Cada jugador dispone de 5 barcos de diferente tamaño:
                *Portaaviones[5]
                *Buque[4]
                *Crucero[3]
                *Submarino[3]
                *Destructor[2]
                - Haz clic en el tablero enemigo para disparar.
                - Azul: agua, blanco: Fallaste :(, rojo: ¡Le diste!
                - Tu tablero muestra tus propios barcos en color gris.
                - El primer jugador en hundir todos los barcos enemigos, gana.
                - ¡Buena suerte, capitán!
                """);
        instrucciones.setEditable(false);
        instrucciones.setWrapStyleWord(true);
        instrucciones.setLineWrap(true);
        instrucciones.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instrucciones.setMargin(new Insets(10, 10, 10, 10));
        inst.add(new JScrollPane(instrucciones), BorderLayout.CENTER);
        // volver menú
        JButton botonVolver = new JButton("Volver al menú principal");
        botonVolver.addActionListener(e -> {
            inst.dispose();
            mostrarMenuPrincipal();
        });
        JPanel panelBoton = new JPanel();
        panelBoton.add(botonVolver);
        inst.add(panelBoton, BorderLayout.SOUTH);
        inst.setVisible(true);
    }

    public void recogerDatos() {// metodo para recoger los datos necesarios pa iniciar el juego
        JDialog dialogo = new JDialog((Frame) null, "Configuración inicial", true);
        dialogo.setSize(800, 500);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new GridLayout(5, 2, 10, 10));

        dialogo.add(new JLabel("Ingrese su nombre: "));
        JTextField espacioNombre = new JTextField();
        dialogo.add(espacioNombre);

        dialogo.add(new JLabel("Seleccione el tamaño del tablero (5-15): "));
        SpinnerNumberModel selTam = new SpinnerNumberModel(5, 5, 15, 1);
        JSpinner spinnerTam = new JSpinner(selTam);
        ((JSpinner.DefaultEditor) spinnerTam.getEditor()).getTextField().setEditable(false);
        dialogo.add(spinnerTam);

        dialogo.add(new JLabel("¿Desea ubicar los barcos manualmente?"));
        JCheckBox manual = new JCheckBox("sip");
        dialogo.add(manual);

        dialogo.add(new JLabel("Elija la dificultad: Principiante(1), intermedio(2), dificil(3), hacker(4)"));
        SpinnerNumberModel selDif = new SpinnerNumberModel(2, 1, 4, 1);
        JSpinner spinnerDif = new JSpinner(selDif);
        ((JSpinner.DefaultEditor) spinnerDif.getEditor()).getTextField().setEditable(false);
        dialogo.add(spinnerDif);

        JButton confirmar = new JButton("Confirmar");
        dialogo.add(new JLabel());
        dialogo.add(confirmar);

        confirmar.addActionListener(e -> {
            String nombre = espacioNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "el nombre no puede estar vacío");
                return;
            }
            this.nombreJugador = nombre;
            this.tamañoTab = (int) spinnerTam.getValue();
            this.ubicacionMan = manual.isSelected();
            this.dificultad = (int) spinnerDif.getValue();
            dialogo.dispose();

        });
        dialogo.setVisible(true);
        
    }
        public String getNombreJugador() {
        return nombreJugador;
    }

    public int getTamañoTablero() {
        return tamañoTab;
    }

    public boolean isUbicacionManual() {
        return ubicacionMan;
    }

    public int getDificultad() {
        return dificultad;
    }
}

