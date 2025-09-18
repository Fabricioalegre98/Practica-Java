    package main;

import javax.swing.*;
import java.awt.*;
import gestor.GestorReparaciones;
import ventas.SimuladorVentas;
import turnos.AgendaTurnos;

    public class InterfazFATech extends JFrame {
        private GestorReparaciones gestor;
        private SimuladorVentas ventas;
        private AgendaTurnos turnos;

        public InterfazFATech() {
            gestor = new GestorReparaciones();
            ventas = new SimuladorVentas();
            turnos = new AgendaTurnos();

            setTitle("FATech - Sistema Integral");
            setSize(400, 300);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1));

            JButton btnReparaciones = new JButton("Gestor de Reparaciones");
            JButton btnVentas = new JButton("Simulador de Ventas");
            JButton btnTurnos = new JButton("Control de Turnos");
            JButton btnSalir = new JButton("Salir");

            btnReparaciones.addActionListener(e -> gestor.mostrarVentana());
            btnVentas.addActionListener(e -> ventas.mostrarVentana());
            btnTurnos.addActionListener(e -> turnos.mostrarVentana());
            btnSalir.addActionListener(e -> System.exit(0));

            add(btnReparaciones);
            add(btnVentas);
            add(btnTurnos);
            add(btnSalir);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new InterfazFATech().setVisible(true));
        }
    }


