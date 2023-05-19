import java.io.IOException;
import javax.swing.JFrame;

public class TetrisGO {

    public static final int WIDTH = 325, HEIGHT = 640;//tamaño del tablero del juego

    private Tablero board;

    private JFrame window;

    private static Server server;


    public TetrisGO() {
        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        board = new Tablero();
        window.add(board);
        window.addKeyListener(board);
        window.setVisible(true);
    }

    public static void  iniciarServidor(int port) {
        server = new Server();
        try {
            server.start(port);
            System.out.println("Servidor iniciado en el puerto " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MenuT ventanaMenu = new MenuT();
        Panas multijugador = new Panas();

        Thread serverThread = new Thread(() -> {
            iniciarServidor(multijugador.getPort());
        });

        serverThread.start();

    }
    
}