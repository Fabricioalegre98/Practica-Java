package gestor;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Establecer el Look and Feel de Nimbus para una apariencia más moderna
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Si Nimbus no está disponible, se usará el Look and Feel por defecto.
        }

        SwingUtilities.invokeLater(() -> {
            GestorReparaciones gestor = new GestorReparaciones();
            gestor.setVisible(true); // El método correcto para mostrar un JFrame
        });
    }
}
