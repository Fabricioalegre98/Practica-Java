package turnos;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            AgendaTurnos agenda = new AgendaTurnos();
            agenda.mostrarVentana();
        });
    }
}
