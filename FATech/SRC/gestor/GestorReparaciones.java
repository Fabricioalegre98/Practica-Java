package gestor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class GestorReparaciones extends JFrame {

    // Modelo de datos
    private final List<Reparacion> listaReparaciones = new ArrayList<>();
    private boolean datosModificados = false;

    // Componentes UI
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField campoCliente, campoEquipo, campoFalla;
    private JFormattedTextField campoFecha;
    private JComboBox<String> comboEstado;
    private JTextField campoBusquedaCliente;
    private JComboBox<String> comboBusquedaEstado;

    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    // Estados predefinidos
    private static final String[] ESTADOS = {"Pendiente", "En Proceso", "Completado", "Cancelado"};

    public GestorReparaciones() {
        formatoFecha.setLenient(false);
        initComponents();
        configurarVentana();
        actualizarTabla();
    }

    private void initComponents() {
        // Panel formulario con GroupLayout para mejor diseño
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Datos de la Reparación", TitledBorder.LEFT, TitledBorder.TOP));

        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblEquipo = new JLabel("Equipo:");
        JLabel lblFalla = new JLabel("Falla:");
        JLabel lblEstado = new JLabel("Estado:");
        JLabel lblFecha = new JLabel("Fecha (dd/MM/yyyy):");

        campoCliente = new JTextField(20);
        campoEquipo = new JTextField(20);
        campoFalla = new JTextField(20);

        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            campoFecha = new JFormattedTextField(mask);
            campoFecha.setColumns(10);
        } catch (ParseException e) {
            campoFecha = new JFormattedTextField();
            campoFecha.setColumns(10);
        }

        comboEstado = new JComboBox<>(ESTADOS);

        GroupLayout layout = new GroupLayout(panelFormulario);
        panelFormulario.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCliente)
                        .addComponent(lblEquipo)
                        .addComponent(lblFalla)
                        .addComponent(lblEstado)
                        .addComponent(lblFecha))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(campoCliente)
                        .addComponent(campoEquipo)
                        .addComponent(campoFalla)
                        .addComponent(comboEstado)
                        .addComponent(campoFecha))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCliente)
                        .addComponent(campoCliente))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEquipo)
                        .addComponent(campoEquipo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFalla)
                        .addComponent(campoFalla))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEstado)
                        .addComponent(comboEstado))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFecha)
                        .addComponent(campoFecha))
        );

        // Tabla y modelo
        modeloTabla = new DefaultTableModel(new Object[]{"Cliente", "Equipo", "Falla", "Estado", "Fecha"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable directamente
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoCreateRowSorter(true); // Permite ordenar columnas

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(800, 250));

        // Panel botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        // Atajos de teclado
        btnAgregar.setMnemonic(KeyEvent.VK_A);
        btnEditar.setMnemonic(KeyEvent.VK_E);
        btnEliminar.setMnemonic(KeyEvent.VK_D);
        btnLimpiar.setMnemonic(KeyEvent.VK_L);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Panel búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar / Filtrar"));

        panelBusqueda.add(new JLabel("Cliente:"));
        campoBusquedaCliente = new JTextField(15);
        panelBusqueda.add(campoBusquedaCliente);

        panelBusqueda.add(new JLabel("Estado:"));
        String[] estadosBusqueda = new String[ESTADOS.length + 1];
        estadosBusqueda[0] = "Todos";
        System.arraycopy(ESTADOS, 0, estadosBusqueda, 1, ESTADOS.length);
        comboBusquedaEstado = new JComboBox<>(estadosBusqueda);
        panelBusqueda.add(comboBusquedaEstado);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(panelFormulario, BorderLayout.NORTH);
        add(panelBusqueda, BorderLayout.CENTER);
        add(scrollTabla, BorderLayout.SOUTH);
        add(panelBotones, BorderLayout.PAGE_END);

        // Eventos botones
        btnAgregar.addActionListener(e -> agregarReparacion());
        btnEditar.addActionListener(e -> editarReparacion());
        btnEliminar.addActionListener(e -> eliminarReparacion());
        btnLimpiar.addActionListener(e -> limpiarCamposConConfirmacion());

        // Evento selección tabla para cargar datos
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosEnFormulario();
            }
        });

        // Evento para marcar datos modificados
        DocumentListener docListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                datosModificados = true;
            }

            public void removeUpdate(DocumentEvent e) {
                datosModificados = true;
            }

            public void insertUpdate(DocumentEvent e) {
                datosModificados = true;
            }
        };
        campoCliente.getDocument().addDocumentListener(docListener);
        campoEquipo.getDocument().addDocumentListener(docListener);
        campoFalla.getDocument().addDocumentListener(docListener);
        campoFecha.getDocument().addDocumentListener(docListener);

        comboEstado.addActionListener(e -> datosModificados = true);

        // Eventos búsqueda para filtrar tabla
        campoBusquedaCliente.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }
        });
        comboBusquedaEstado.addActionListener(e -> filtrarTabla());

        // Confirmar salida si hay cambios
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salirConConfirmacion();
            }
        });
    }

    private void configurarVentana() {
        setTitle("Gestor de Reparaciones - v3.0 Mejorado");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void agregarReparacion() {
        if (!validarCampos()) return;

        Reparacion rep = new Reparacion(
                campoCliente.getText().trim(),
                campoEquipo.getText().trim(),
                campoFalla.getText().trim(),
                Objects.requireNonNull(comboEstado.getSelectedItem()).toString(),
                parseFecha(campoFecha.getText().trim())
        );

        listaReparaciones.add(rep);
        datosModificados = true;
        actualizarTabla();
        limpiarCampos();
        JOptionPane.showMessageDialog(this, "Reparación agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarReparacion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) return;

        // Obtener índice real con sorter
        fila = tabla.convertRowIndexToModel(fila);

        Reparacion rep = listaReparaciones.get(fila);
        rep.setCliente(campoCliente.getText().trim());
        rep.setEquipo(campoEquipo.getText().trim());
        rep.setFalla(campoFalla.getText().trim());
        rep.setEstado(Objects.requireNonNull(comboEstado.getSelectedItem()).toString());
        rep.setFecha(parseFecha(campoFecha.getText().trim()));

        datosModificados = true;
        actualizarTabla();
        JOptionPane.showMessageDialog(this, "Reparación actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarReparacion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        fila = tabla.convertRowIndexToModel(fila);
        Reparacion rep = listaReparaciones.get(fila);

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar la reparación de: " + rep.getCliente() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaReparaciones.remove(fila);
            datosModificados = true;
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Reparación eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void limpiarCamposConConfirmacion() {
        if (tieneDatosIngresados()) {
            int opcion = JOptionPane.showConfirmDialog(this,
                    "Hay datos ingresados en el formulario. ¿Desea limpiar los campos?",
                    "Confirmar limpieza",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }
        }
        limpiarCampos();
    }

    private boolean tieneDatosIngresados() {
        return !campoCliente.getText().trim().isEmpty() ||
                !campoEquipo.getText().trim().isEmpty() ||
                !campoFalla.getText().trim().isEmpty() ||
                !campoFecha.getText().trim().isEmpty() ||
                comboEstado.getSelectedIndex() != 0;
    }

    private void limpiarCampos() {
        campoCliente.setText("");
        campoEquipo.setText("");
        campoFalla.setText("");
        campoFecha.setValue(null);
        comboEstado.setSelectedIndex(0);
        tabla.clearSelection();
        campoCliente.requestFocus();
    }

    private void cargarDatosEnFormulario() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            fila = tabla.convertRowIndexToModel(fila);
            Reparacion rep = listaReparaciones.get(fila);
            campoCliente.setText(rep.getCliente());
            campoEquipo.setText(rep.getEquipo());
            campoFalla.setText(rep.getFalla());
            comboEstado.setSelectedItem(rep.getEstado());
            campoFecha.setText(formatoFecha.format(rep.getFecha()));
        }
    }

    private boolean validarCampos() {
        if (campoCliente.getText().trim().isEmpty()) {
            mostrarError("El campo Cliente es obligatorio", campoCliente);
            return false;
        }
        if (campoCliente.getText().trim().length() > 50) {
            mostrarError("El nombre del cliente no puede exceder 50 caracteres", campoCliente);
            return false;
        }
        if (campoEquipo.getText().trim().isEmpty()) {
            mostrarError("El campo Equipo es obligatorio", campoEquipo);
            return false;
        }
        if (campoEquipo.getText().trim().length() > 50) {
            mostrarError("El nombre del equipo no puede exceder 50 caracteres", campoEquipo);
            return false;
        }
        if (campoFalla.getText().trim().isEmpty()) {
            mostrarError("El campo Falla es obligatorio", campoFalla);
            return false;
        }
        if (campoFecha.getText().trim().isEmpty() || campoFecha.getText().contains("_")) {
            mostrarError("El campo Fecha es obligatorio y debe estar completo", campoFecha);
            return false;
        }
        try {
            formatoFecha.parse(campoFecha.getText().trim());
        } catch (ParseException e) {
            mostrarError("Formato de fecha inválido. Use dd/MM/yyyy (ejemplo: 25/12/2023)", campoFecha);
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

    private Date parseFecha(String texto) {
        try {
            return formatoFecha.parse(texto);
        } catch (ParseException e) {
            return new Date();
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Reparacion rep : listaReparaciones) {
            modeloTabla.addRow(new Object[]{
                    rep.getCliente(),
                    rep.getEquipo(),
                    rep.getFalla(),
                    rep.getEstado(),
                    formatoFecha.format(rep.getFecha())
            });
        }
        filtrarTabla();
    }

    private void filtrarTabla() {
        String filtroCliente = campoBusquedaCliente.getText().trim().toLowerCase();
        String filtroEstado = Objects.requireNonNull(comboBusquedaEstado.getSelectedItem()).toString();

        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tabla.getRowSorter();

        RowFilter<DefaultTableModel, Object> rf = new RowFilter<>() {
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String cliente = entry.getStringValue(0).toLowerCase();
                String estado = entry.getStringValue(3);
                boolean clienteMatch = cliente.contains(filtroCliente);
                boolean estadoMatch = filtroEstado.equals("Todos") || estado.equals(filtroEstado);
                return clienteMatch && estadoMatch;
            }
        };
        sorter.setRowFilter(rf);
    }

    private void salirConConfirmacion() {
        if (datosModificados) {
            int opcion = JOptionPane.showConfirmDialog(this,
                    "Hay cambios no guardados. ¿Desea salir de todos modos?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (opcion == JOptionPane.NO_OPTION) {
                return;
            }
        }
        dispose();
    }
}
