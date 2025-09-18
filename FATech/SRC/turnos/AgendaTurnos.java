package turnos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaTurnos extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField campoCliente, campoTelefono, campoFecha, campoHora;
    private JComboBox<String> comboServicio, comboEstado;
    private JTextArea areaObservaciones;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

    public AgendaTurnos() {
        initComponents();
        configurarVentana();
        configurarEventos();
    }

    private void initComponents() {
        // Configurar formatos
        formatoFecha.setLenient(false);
        formatoHora.setLenient(false);

        // Modelo de la tabla
        modeloTabla = new DefaultTableModel(
                new Object[]{"Cliente", "Teléfono", "Servicio", "Fecha", "Hora", "Estado", "Observaciones"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable directamente
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);

        // Ajustar ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(120); // Cliente
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100); // Teléfono
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100); // Servicio
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);  // Fecha
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);  // Hora
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);  // Estado
        tabla.getColumnModel().getColumn(6).setPreferredWidth(150); // Observaciones

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(800, 250));

        // Panel de formulario principal
        JPanel panelFormulario = new JPanel(new BorderLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Turno"));

        // Panel de campos básicos
        JPanel panelCampos = new JPanel(new GridLayout(3, 4, 10, 10));

        // Campos del formulario
        campoCliente = new JTextField();
        campoTelefono = new JTextField();
        campoFecha = new JTextField();
        campoHora = new JTextField();

        // ComboBox para servicios
        String[] servicios = {
                "Corte de Cabello",
                "Barba",
                "Corte + Barba",
                "Tinte",
                "Peinado",
                "Manicure",
                "Pedicure",
                "Tratamiento Capilar",
                "Otro"
        };
        comboServicio = new JComboBox<>(servicios);

        // ComboBox para estados
        String[] estados = {"Programado", "Confirmado", "En Curso", "Completado", "Cancelado", "No Asistió"};
        comboEstado = new JComboBox<>(estados);

        // Agregar componentes al panel de campos
        panelCampos.add(new JLabel("Cliente:"));
        panelCampos.add(campoCliente);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(campoTelefono);

        panelCampos.add(new JLabel("Servicio:"));
        panelCampos.add(comboServicio);
        panelCampos.add(new JLabel("Estado:"));
        panelCampos.add(comboEstado);

        panelCampos.add(new JLabel("Fecha (dd/MM/yyyy):"));
        panelCampos.add(campoFecha);
        panelCampos.add(new JLabel("Hora (HH:mm):"));
        panelCampos.add(campoHora);

        // Panel de observaciones
        JPanel panelObservaciones = new JPanel(new BorderLayout());
        panelObservaciones.add(new JLabel("Observaciones:"), BorderLayout.NORTH);
        areaObservaciones = new JTextArea(3, 30);
        areaObservaciones.setLineWrap(true);
        areaObservaciones.setWrapStyleWord(true);
        JScrollPane scrollObservaciones = new JScrollPane(areaObservaciones);
        panelObservaciones.add(scrollObservaciones, BorderLayout.CENTER);

        // Agregar paneles al formulario
        panelFormulario.add(panelCampos, BorderLayout.NORTH);
        panelFormulario.add(panelObservaciones, BorderLayout.CENTER);

        // Botones
        JButton btnAgregar = new JButton("Agregar Turno");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnBuscar = new JButton("Buscar");

        // Colores de botones
        btnAgregar.setBackground(new Color(76, 175, 80));
        btnAgregar.setForeground(Color.WHITE);
        btnEditar.setBackground(new Color(33, 150, 243));
        btnEditar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);
        btnBuscar.setBackground(new Color(156, 39, 176));
        btnBuscar.setForeground(Color.WHITE);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnBuscar);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Configurar eventos de botones
        btnAgregar.addActionListener(e -> agregarTurno());
        btnEditar.addActionListener(e -> editarTurno());
        btnEliminar.addActionListener(e -> eliminarTurno());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnBuscar.addActionListener(e -> buscarTurno());
    }

    private void configurarVentana() {
        setTitle("Control de Turnos - Agenda Digital");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Confirmar antes de cerrar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        AgendaTurnos.this,
                        "¿Está seguro que desea salir?\nLos turnos no guardados se perderán.",
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
        ActionListener agregarConEnter = e -> agregarTurno();
        campoCliente.addActionListener(agregarConEnter);
        campoTelefono.addActionListener(agregarConEnter);
        campoFecha.addActionListener(agregarConEnter);
        campoHora.addActionListener(agregarConEnter);
    }

    private void agregarTurno() {
        if (!validarCampos()) {
            return;
        }

        try {
            modeloTabla.addRow(new Object[]{
                    campoCliente.getText().trim(),
                    campoTelefono.getText().trim(),
                    comboServicio.getSelectedItem().toString(),
                    campoFecha.getText().trim(),
                    campoHora.getText().trim(),
                    comboEstado.getSelectedItem().toString(),
                    areaObservaciones.getText().trim()
            });

            limpiarCampos();
            JOptionPane.showMessageDialog(this,
                    "Turno agregado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al agregar el turno: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarTurno() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un turno para editar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            modeloTabla.setValueAt(campoCliente.getText().trim(), fila, 0);
            modeloTabla.setValueAt(campoTelefono.getText().trim(), fila, 1);
            modeloTabla.setValueAt(comboServicio.getSelectedItem().toString(), fila, 2);
            modeloTabla.setValueAt(campoFecha.getText().trim(), fila, 3);
            modeloTabla.setValueAt(campoHora.getText().trim(), fila, 4);
            modeloTabla.setValueAt(comboEstado.getSelectedItem().toString(), fila, 5);
            modeloTabla.setValueAt(areaObservaciones.getText().trim(), fila, 6);

            JOptionPane.showMessageDialog(this,
                    "Turno actualizado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al editar el turno: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarTurno() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un turno para eliminar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cliente = getValueSafe(fila, 0);
        String fecha = getValueSafe(fila, 3);
        String hora = getValueSafe(fila, 4);

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar el turno de:\n" +
                        cliente + " - " + fecha + " a las " + hora + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                modeloTabla.removeRow(fila);
                limpiarCampos();
                JOptionPane.showMessageDialog(this,
                        "Turno eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al eliminar el turno: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarTurno() {
        String busqueda = JOptionPane.showInputDialog(this,
                "Ingrese el nombre del cliente a buscar:",
                "Buscar Turno",
                JOptionPane.QUESTION_MESSAGE);

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            busqueda = busqueda.toLowerCase().trim();

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String cliente = getValueSafe(i, 0).toLowerCase();
                if (cliente.contains(busqueda)) {
                    tabla.setRowSelectionInterval(i, i);
                    tabla.scrollRectToVisible(tabla.getCellRect(i, 0, true));
                    return;
                }
            }

            JOptionPane.showMessageDialog(this,
                    "No se encontraron turnos para: " + busqueda,
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarDatosEnFormulario() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            try {
                campoCliente.setText(getValueSafe(fila, 0));
                campoTelefono.setText(getValueSafe(fila, 1));
                comboServicio.setSelectedItem(getValueSafe(fila, 2));
                campoFecha.setText(getValueSafe(fila, 3));
                campoHora.setText(getValueSafe(fila, 4));
                comboEstado.setSelectedItem(getValueSafe(fila, 5));
                areaObservaciones.setText(getValueSafe(fila, 6));
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

        // Validar teléfono
        String telefono = campoTelefono.getText().trim();
        if (telefono.isEmpty()) {
            mostrarError("El campo Teléfono es obligatorio", campoTelefono);
            return false;
        }

        // Validar que el teléfono contenga solo números, espacios y guiones
        if (!telefono.matches("[0-9\\s\\-\\+\\(\\)]+")) {
            mostrarError("El teléfono contiene caracteres inválidos", campoTelefono);
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
            mostrarError("Formato de fecha inválido. Use dd/MM/yyyy", campoFecha);
            return false;
        }

        // Validar hora
        String hora = campoHora.getText().trim();
        if (hora.isEmpty()) {
            mostrarError("El campo Hora es obligatorio", campoHora);
            return false;
        }

        try {
            formatoHora.parse(hora);
        } catch (ParseException e) {
            mostrarError("Formato de hora inválido. Use HH:mm (ejemplo: 14:30)", campoHora);
            return false;
        }

        // Validar longitud de campos
        if (campoCliente.getText().trim().length() > 50) {
            mostrarError("El nombre del cliente no puede exceder 50 caracteres", campoCliente);
            return false;
        }

        return true;
    }

    private void mostrarError(String mensaje, JComponent campo) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
        campo.requestFocus();
        if (campo instanceof JTextField) {
            ((JTextField) campo).selectAll();
        }
    }

    private void limpiarCampos() {
        campoCliente.setText("");
        campoTelefono.setText("");
        campoFecha.setText("");
        campoHora.setText("");
        areaObservaciones.setText("");
        comboServicio.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        campoCliente.requestFocus();
        tabla.clearSelection();
    }

    // Método para cargar datos de ejemplo
    public void cargarDatosEjemplo() {
        modeloTabla.addRow(new Object[]{"Juan Pérez", "011-4567-8901", "Reparación PC - Hardware", "16/09/2024", "10:00", "Confirmado", "Problema con la fuente de poder"});
        modeloTabla.addRow(new Object[]{"Ana García", "011-2345-6789", "Reparación Celular - Pantalla", "16/09/2024", "14:30", "Programado", "iPhone 12 - Pantalla rota"});
        modeloTabla.addRow(new Object[]{"Carlos López", "011-8765-4321", "Mantenimiento PC", "17/09/2024", "09:15", "Programado", "Limpieza general y actualización"});
        modeloTabla.addRow(new Object[]{"María Rodríguez", "011-5555-1234", "Reparación Consola", "17/09/2024", "16:00", "Confirmado", "PS5 - No lee discos"});
    }

    public void mostrarVentana() {
        setVisible(true);
    }

    // Método main para testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgendaTurnos agenda = new AgendaTurnos();
            agenda.cargarDatosEjemplo(); // Opcional: cargar datos de ejemplo
            agenda.mostrarVentana();
        });
    }
}