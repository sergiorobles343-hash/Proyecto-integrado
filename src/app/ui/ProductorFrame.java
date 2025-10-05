/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ui;

/**
 *
 * @author sergi
 */


import app.db.PredioDatabase;
import app.db.InspeccionDatabase;
import app.model.Predio;
import app.model.Productor;
import app.model.Inspeccion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductorFrame extends JFrame {
    private JTextField txtNombre, txtUbicacion, txtArea, txtCultivo;
    private JTable tablaPredios, tablaInspecciones;
    private DefaultTableModel modeloTablaPredios, modeloTablaInspecciones;
    private JTextArea areaNotificaciones;
    private Productor productor;

    // Constructor con Productor
    public ProductorFrame(Productor productor) {
        this();
        this.productor = productor;
        setTitle("🌱 Gestión del Productor - " + productor.getNombre());
        cargarPredios();
        cargarHistorialInspecciones();
    }

    // Constructor por defecto
    public ProductorFrame() {
        setTitle("🌱 Gestión del Productor");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fuente global
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 14));

        // Pestañas con estilo
        JTabbedPane pestañas = new JTabbedPane();
        pestañas.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pestañas.setBackground(new Color(245, 245, 245));

        pestañas.addTab("🏡 Predios", crearPanelPredios());
        pestañas.addTab("📋 Historial de Inspecciones", crearPanelHistorial());
        pestañas.addTab("🔔 Notificaciones", crearPanelNotificaciones());

        add(pestañas);
    }

    // ============================= PANEL PREDIOS =============================
    private JPanel crearPanelPredios() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulario lateral
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));

        txtNombre = new JTextField();
        txtUbicacion = new JTextField();
        txtArea = new JTextField();
        txtCultivo = new JTextField();

        panelForm.add(new JLabel("Nombre del predio:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Ubicación:"));
        panelForm.add(txtUbicacion);
        panelForm.add(new JLabel("Área (Ha):"));
        panelForm.add(txtArea);
        panelForm.add(new JLabel("Cultivo principal:"));
        panelForm.add(txtCultivo);

        JButton btnAgregar = crearBoton("➕ Agregar Predio", new Color(0, 153, 76));
        JButton btnEliminar = crearBoton("🗑 Eliminar Predio", new Color(204, 0, 0));

        panelForm.add(btnAgregar);
        panelForm.add(btnEliminar);

        panel.add(panelForm, BorderLayout.WEST);

        // Tabla de predios
        String[] columnas = {"Nombre", "Ubicación", "Área (Ha)", "Cultivo"};
        modeloTablaPredios = new DefaultTableModel(columnas, 0);
        tablaPredios = new JTable(modeloTablaPredios);

        // Estilo de cabecera
        tablaPredios.getTableHeader().setBackground(new Color(0, 102, 204));
        tablaPredios.getTableHeader().setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) tablaPredios.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollTabla = new JScrollPane(tablaPredios);
        panel.add(scrollTabla, BorderLayout.CENTER);

        // Acción Agregar
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String areaStr = txtArea.getText().trim();
            String cultivo = txtCultivo.getText().trim();

            if (nombre.isEmpty() || ubicacion.isEmpty() || areaStr.isEmpty() || cultivo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "⚠ Complete todos los campos");
                return;
            }

            try {
                double area = Double.parseDouble(areaStr);
                Predio nuevo = new Predio(nombre, ubicacion, area, cultivo);
                PredioDatabase.agregarPredio(nuevo);
                modeloTablaPredios.addRow(new Object[]{nombre, ubicacion, area, cultivo});
                txtNombre.setText(""); txtUbicacion.setText(""); txtArea.setText(""); txtCultivo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "⚠ Área debe ser un número válido");
            }
        });

        // Acción Eliminar
        btnEliminar.addActionListener(e -> {
            int fila = tablaPredios.getSelectedRow();
            if (fila >= 0) {
                String nombre = (String) modeloTablaPredios.getValueAt(fila, 0);
                if (PredioDatabase.eliminarPredio(nombre)) {
                    modeloTablaPredios.removeRow(fila);
                    JOptionPane.showMessageDialog(null, "✅ Predio eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "❌ No se pudo eliminar el predio");
                }
            } else {
                JOptionPane.showMessageDialog(null, "⚠ Seleccione un predio en la tabla");
            }
        });

        return panel;
    }

    // ============================= PANEL HISTORIAL =============================
    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnas = {"Predio", "Fecha", "Técnico", "Hallazgos"};
        modeloTablaInspecciones = new DefaultTableModel(columnas, 0);
        tablaInspecciones = new JTable(modeloTablaInspecciones);

        tablaInspecciones.getTableHeader().setBackground(new Color(0, 102, 204));
        tablaInspecciones.getTableHeader().setForeground(Color.WHITE);

        panel.add(new JScrollPane(tablaInspecciones), BorderLayout.CENTER);
        return panel;
    }

    // ============================= PANEL NOTIFICACIONES =============================
    private JPanel crearPanelNotificaciones() {
        JPanel panel = new JPanel(new BorderLayout());

        areaNotificaciones = new JTextArea();
        areaNotificaciones.setEditable(false);
        areaNotificaciones.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaNotificaciones.setBackground(new Color(30, 30, 30));
        areaNotificaciones.setForeground(new Color(0, 255, 128));
        areaNotificaciones.setText("✅ No hay nuevas notificaciones.");

        panel.add(new JScrollPane(areaNotificaciones), BorderLayout.CENTER);
        return panel;
    }

    // ============================= MÉTODOS DE CARGA =============================
    private void cargarPredios() {
        if (productor == null) return;
        modeloTablaPredios.setRowCount(0);
        for (Predio p : productor.getPredios()) {
            modeloTablaPredios.addRow(new Object[]{p.getNombre(), p.getUbicacion(), p.getArea(), p.getCultivo()});
        }
    }

    private void cargarHistorialInspecciones() {
        if (productor == null) return;
        modeloTablaInspecciones.setRowCount(0);
        for (Predio p : productor.getPredios()) {
            for (Inspeccion i : InspeccionDatabase.obtenerInspeccionesPorPredio(p.getNombre())) {
                modeloTablaInspecciones.addRow(new Object[]{
                i.getPredio(),
                i.getFecha(),
                i.getTecnicoId(),
                i.getCultivo(),
                i.getPlaga(),
                i.getObservaciones()
});
            }
        }
    }

    // Agregar notificación
    public void agregarNotificacion(String mensaje) {
        areaNotificaciones.append("\n🔔 " + mensaje);
    }

    // ============================= BOTONES BONITOS =============================
    private JButton crearBoton(String texto, Color colorBase) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setBackground(colorBase);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Efecto hover
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(colorBase.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(colorBase);
            }
        });
        return btn;
    }
}