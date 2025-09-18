package reparaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestorReparaciones extends JFrame {
    private JTextField txtCliente, txtDescripcion, txtFecha;
    private JComboBox<String> cmbEstado;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public GestorReparaciones() {
        setTitle("Gestor de Reparaciones");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos de Reparación"));

        panelForm.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        panelForm.add(txtCliente);

        panelForm.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelForm.add(txtDescripcion);

        panelForm.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        panelForm.add(txtFecha);

        panelForm.add(new JLabel("Estado:"));
        cmbEstado = new JComboBox<>(new String[]{"Pendiente", "En Proceso", "Finalizado"});
        panelForm.add(cmbEstado);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Tabla de reparaciones
        modeloTabla = new DefaultTableModel(new Object[]{"Cliente", "Descripción", "Fecha", "Estado"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(panelForm, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones de botones (vacías por ahora)
        btnAgregar.addActionListener(e -> {/* lógica a implementar */});
        btnEditar.addActionListener(e -> {/* lógica a implementar */});
        btnEliminar.addActionListener(e -> {/* lógica a implementar */});
        btnLimpiar.addActionListener(e -> {/* lógica a implementar */});
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}