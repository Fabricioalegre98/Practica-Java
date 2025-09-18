package ventas;

import javax.swing.*;
import java.awt.*;

public class SimuladorVentas extends JFrame {

    public SimuladorVentas() {
        setTitle("Simulador de Ventas");
        setSize(300, 200);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea areaVentas = new JTextArea("Aquí puedes simular ventas...");
        areaVentas.setEditable(false);
        add(new JScrollPane(areaVentas), BorderLayout.CENTER);
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}