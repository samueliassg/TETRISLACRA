import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TablaPuntos {
    private static List<Jugador> jugadores;
    private JFrame TablaPuntosFrame;
    private JTable tablaJugadores;

    public TablaPuntos() {
        if (jugadores == null) {
            jugadores = new ArrayList<>();
        }

        // Configurar el UIManager para cambiar los valores predeterminados de apariencia
        Color darkGray = new Color(40, 40, 40); // Gris oscuro personalizado
        UIManager.put("OptionPane.messageForeground", Color.WHITE); // Color del texto del cuadro de diálogo
        UIManager.put("OptionPane.background", darkGray); // Color de fondo del cuadro de diálogo
        UIManager.put("Panel.background", darkGray); // Color de fondo del panel dentro del cuadro de diálogo
        UIManager.put("Label.foreground", Color.WHITE); // Color del texto de las etiquetas
        UIManager.put("Table.background", darkGray); // Color de fondo de la tabla
        UIManager.put("Table.foreground", Color.WHITE); // Color del texto de la tabla

        TablaPuntosFrame = new JFrame("TETRIS DEFINITIVO");
        tablaJugadores = new JTable();
    }

    public static void agregarJugador(Jugador jugador) {
        jugadores.stream().filter(j -> j.getNombre().equals(jugador.getNombre())).findFirst().ifPresentOrElse(j -> agregarOReemplazar(j, jugador), () -> jugadores.add(jugador));
    }

    private static void agregarOReemplazar(Jugador oldJugador, Jugador newJugador) {
        if (oldJugador.getScore() < newJugador.getScore()) {
            jugadores.remove(oldJugador);
            jugadores.add(newJugador);
        }
    }

    public void mostrarTabla() {
        // Ordenar la lista de jugadores por puntuación de mayor a menor
        jugadores.sort(Collections.reverseOrder());
    
        // Crear un modelo de tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Jugadores");
        modeloTabla.addColumn("Puntuación");
    
        // Agregar los datos de los jugadores al modelo de tabla
        for (Jugador jugador : jugadores) {
            modeloTabla.addRow(new Object[]{jugador.getNombre(), jugador.getScore()});
        }
    
        // Crear la tabla y configurar el aspecto
        JTable tablaJugadores = new JTable(modeloTabla);
        tablaJugadores.setBackground(Color.DARK_GRAY);
        tablaJugadores.setForeground(Color.WHITE);
        tablaJugadores.setSelectionBackground(Color.GRAY);
        tablaJugadores.setSelectionForeground(Color.WHITE);
    
        // Crear el JScrollPane y configurar el aspecto
        JScrollPane scrollPane = new JScrollPane(tablaJugadores);
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.setPreferredSize(new Dimension(300, 200)); // Establecer el tamaño preferido del JScrollPane
    
        // Configurar el panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.DARK_GRAY);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
    
        // Configurar la ventana y mostrarla
        JFrame TablaPuntosFrame = new JFrame("Tabla de Puntos");
        TablaPuntosFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        TablaPuntosFrame.getContentPane().setBackground(Color.DARK_GRAY);
        TablaPuntosFrame.getContentPane().add(panelPrincipal);
        TablaPuntosFrame.pack();
        TablaPuntosFrame.setLocationRelativeTo(null);
        TablaPuntosFrame.setVisible(true);
    }
    

    public List<Jugador> getJugadores() {
        return jugadores.stream().sorted().toList();
    }
}
