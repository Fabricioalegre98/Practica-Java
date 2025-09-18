package turnos;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnTurnos = new JButton("Control de Turnos");
        JButton btnVentas = new JButton("Simulador de Ventas");
        JButton btnReparaciones = new JButton("Gestor de Reparaciones");

        btnTurnos.addActionListener(e -> {
            AgendaTurnos agenda = new AgendaTurnos();
            agenda.mostrarVentana();
        });

        btnVentas.addActionListener(e -> {
            ventas.SimuladorVentas ventas = new ventas.SimuladorVentas();
            ventas.mostrarVentana();
        });

        btnReparaciones.addActionListener(e -> {
            GestorReparaciones gestor = new GestorReparaciones();
            gestor.mostrarVentana();
        });

        JPanel panel = new JPanel();
        panel.add(btnTurnos);
        panel.add(btnVentas);
        panel.add(btnReparaciones);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}