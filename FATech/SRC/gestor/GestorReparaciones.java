package gestor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestorReparaciones extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField campoCliente, campoEquipo, campoFalla, campoFecha;
    private JComboBox<String> comboEstado;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public GestorReparaciones() {
        initComponents();
        configurarVentana();
        configurarEventos();
    }

    private void initComponents() {
        // Configurar formato de fecha
        formatoFecha.setLenient(false);

        // Modelo de la tabla
        modeloTabla = new DefaultTableModel(
                new Object[]{"Cliente", "Equipo", "Falla", "Estado", "Fecha"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer tabla no editable directamente
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(750, 200));

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 5, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de la Reparación"));

        // Campos del formulario
        campoCliente = new JTextField();
        campoEquipo = new JTextField();
        campoFalla = new JTextField();
        campoFecha = new JTextField();

        // ComboBox para estados predefinidos
        String[] estados = {"Pendiente", "En Proceso", "Completado", "Cancelado"};
        comboEstado = new JComboBox<>(estados);

        // Agregar labels y campos al formulario
        panelFormulario.add(new JLabel("Cliente:"));
        panelFormulario.add(new JLabel("Equipo:"));
        panelFormulario.add(new JLabel("Falla:"));
        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(new JLabel("Fecha (dd/MM/yyyy):"));

        panelFormulario.add(campoCliente);
        panelFormulario.add(campoEquipo);
        panelFormulario.add(campoFalla);
        panelFormulario.add(comboEstado);
        panelFormulario.add(campoFecha);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnAgregar.setBackground(new Color(76, 175, 80));
        btnAgregar.setForeground(Color.WHITE);
        btnEditar.setBackground(new Color(33, 150, 243));
        btnEditar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Configurar eventos de botones
        btnAgregar.addActionListener(e -> agregarReparacion());
        btnEditar.addActionListener(e -> editarReparacion());
        btnEliminar.addActionListener(e -> eliminarReparacion());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void configurarVentana() {
        setTitle("Gestor de Reparaciones - v2.0");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Confirmar antes de cerrar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        GestorReparaciones.this,
                        "¿Está seguro que desea salir?\nLos datos no guardados se perderán.",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    private void configurarEventos() {
        // Al seleccionar una fila, cargar los datos en el formulario
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosEnFormulario();
            }
        });

        // Agregar con Enter en los campos
        ActionListener agregarConEnter = e -> agregarReparacion();
        campoCliente.addActionListener(agregarConEnter);
        campoEquipo.addActionListener(agregarConEnter);
        campoFalla.addActionListener(agregarConEnter);
        campoFecha.addActionListener(agregarConEnter);
    }

    private void agregarReparacion() {
        if (!validarCampos()) {
            return;
        }

        try {
            modeloTabla.addRow(new Object[]{
                    campoCliente.getText().trim(),
                    campoEquipo.getText().trim(),
                    campoFalla.getText().trim(),
                    comboEstado.getSelectedItem().toString(),
                    campoFecha.getText().trim()
            });

            limpiarCampos();
            JOptionPane.showMessageDialog(this,
                    "Reparación agregada correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al agregar la reparación: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarReparacion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una fila para editar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            modeloTabla.setValueAt(campoCliente.getText().trim(), fila, 0);
            modeloTabla.setValueAt(campoEquipo.getText().trim(), fila, 1);
            modeloTabla.setValueAt(campoFalla.getText().trim(), fila, 2);
            modeloTabla.setValueAt(comboEstado.getSelectedItem().toString(), fila, 3);
            modeloTabla.setValueAt(campoFecha.getText().trim(), fila, 4);

            JOptionPane.showMessageDialog(this,
                    "Reparación actualizada correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al editar la reparación: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarReparacion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una fila para eliminar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cliente = getValueSafe(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar la reparación de: " + cliente + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                modeloTabla.removeRow(fila);
                limpiarCampos();
                JOptionPane.showMessageDialog(this,
                        "Reparación eliminada correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al eliminar la reparación: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarDatosEnFormulario() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            try {
                campoCliente.setText(getValueSafe(fila, 0));
                campoEquipo.setText(getValueSafe(fila, 1));
                campoFalla.setText(getValueSafe(fila, 2));
                comboEstado.setSelectedItem(getValueSafe(fila, 3));
                campoFecha.setText(getValueSafe(fila, 4));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al cargar los datos: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getValueSafe(int fila, int columna) {
        Object valor = modeloTabla.getValueAt(fila, columna);
        return valor != null ? valor.toString() : "";
    }

    private boolean validarCampos() {
        // Validar cliente
        if (campoCliente.getText().trim().isEmpty()) {
            mostrarError("El campo Cliente es obligatorio", campoCliente);
            return false;
        }

        // Validar equipo
        if (campoEquipo.getText().trim().isEmpty()) {
            mostrarError("El campo Equipo es obligatorio", campoEquipo);
            return false;
        }

        // Validar falla
        if (campoFalla.getText().trim().isEmpty()) {
            mostrarError("El campo Falla es obligatorio", campoFalla);
            return false;
        }

        // Validar fecha
        String fecha = campoFecha.getText().trim();
        if (fecha.isEmpty()) {
            mostrarError("El campo Fecha es obligatorio", campoFecha);
            return false;
        }

        try {
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            mostrarError("Formato de fecha inválido. Use dd/MM/yyyy (ejemplo: 25/12/2023)", campoFecha);
            return false;
        }

        // Validar longitud de campos
        if (campoCliente.getText().trim().length() > 50) {
            mostrarError("El nombre del cliente no puede exceder 50 caracteres", campoCliente);
            return false;
        }

        if (campoEquipo.getText().trim().length() > 50) {
            mostrarError("El nombre del equipo no puede exceder 50 caracteres", campoEquipo);
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje, JTextField campo) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
        campo.requestFocus();
        campo.selectAll();
    }

    private void limpiarCampos() {
        campoCliente.setText("");
        campoEquipo.setText("");
        campoFalla.setText("");
        campoFecha.setText("");
        comboEstado.setSelectedIndex(0);
        campoCliente.requestFocus();
        tabla.clearSelection();
    }

    // Método para cargar datos de ejemplo (opcional)
    public void cargarDatosEjemplo() {
        modeloTabla.addRow(new Object[]{"Juan Pérez", "Laptop HP", "No enciende", "Pendiente", "15/09/2024"});
        modeloTabla.addRow(new Object[]{"María García", "iPhone 12", "Pantalla rota", "En Proceso", "14/09/2024"});
        modeloTabla.addRow(new Object[]{"Carlos López", "PC Desktop", "Virus", "Completado", "13/09/2024"});
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}