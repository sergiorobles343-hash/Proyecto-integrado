/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ui;

/**
 *
 * @author sergi
 */
import app.model.Inspeccion;
import app.model.Tecnico;
import app.db.InspeccionDatabase;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarInspeccionFrame extends JFrame {

    private Tecnico tecnico; // Técnico logueado

    public RegistrarInspeccionFrame(Tecnico tecnico) {
        this.tecnico = tecnico;

        setTitle("Registrar Nueva Inspección");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Nueva Inspección", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;

        // Predio
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Predio:"), gbc);
        JComboBox<String> cmbPredio = new JComboBox<>(new String[]{
                "Predio La Esperanza", "Predio San Jorge", "Predio El Progreso"
        });
        gbc.gridx = 1;
        panel.add(cmbPredio, gbc);

        // Fecha
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Fecha:"), gbc);
        JTextField txtFecha = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        gbc.gridx = 1;
        panel.add(txtFecha, gbc);

        // Cultivo
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Cultivo:"), gbc);
        JTextField txtCultivo = new JTextField();
        gbc.gridx = 1;
        panel.add(txtCultivo, gbc);

        // Plaga
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Plaga encontrada:"), gbc);
        JTextField txtPlaga = new JTextField();
        gbc.gridx = 1;
        panel.add(txtPlaga, gbc);

        // Observaciones
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Observaciones:"), gbc);
        JTextArea txtObs = new JTextArea(4, 20);
        JScrollPane scrollObs = new JScrollPane(txtObs);
        gbc.gridx = 1;
        panel.add(scrollObs, gbc);

        // Botones
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 153, 0));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(204, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(btnGuardar, gbc);
        gbc.gridx = 1;
        panel.add(btnCancelar, gbc);

        add(panel);

        // Eventos
        btnGuardar.addActionListener(e -> {
            try {
                String predio = (String) cmbPredio.getSelectedItem();
                String cultivo = txtCultivo.getText();
                String plaga = txtPlaga.getText();
                String observaciones = txtObs.getText();
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(txtFecha.getText());

                // Crear inspección con el ID del técnico logueado
                Inspeccion nueva = new Inspeccion(
                        predio,
                        fecha,
                        tecnico.getId(),  // 👈 Guarda el ID del técnico
                        cultivo,
                        plaga,
                        observaciones
                );

                // Guardar en la base de datos
                InspeccionDatabase.agregarInspeccion(nueva);

                JOptionPane.showMessageDialog(this, "✅ Inspección registrada correctamente");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error al registrar inspección: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }

    RegistrarInspeccionFrame() {
       new RegistrarInspeccionFrame(tecnico).setVisible(true);
    }
}