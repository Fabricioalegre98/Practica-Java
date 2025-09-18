package turnos;

import javax.swing.*;
import java.awt.*;

public class GestorReparaciones extends JFrame {
    public GestorReparaciones() {
        setTitle("Gestor de Reparaciones");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Ventana del Gestor de Reparaciones", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}