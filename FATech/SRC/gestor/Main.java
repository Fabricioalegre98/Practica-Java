package gestor;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GestorReparaciones gestor = new GestorReparaciones();
            gestor.mostrarVentana();
        });
    }
}
