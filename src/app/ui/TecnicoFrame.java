/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ui;

/**
 *
 * @author sergi
 */

import app.db.UserDatabase;
import app.model.Tecnico;

import javax.swing.*;
import java.awt.*;

public class TecnicoFrame extends JFrame {

    private Tecnico tecnico;

    public TecnicoFrame(Tecnico tecnico) {
        this.tecnico = tecnico;

        setTitle("Panel Técnico - " + tecnico.getNombre());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblBienvenida = new JLabel("Bienvenido Técnico: " + tecnico.getNombre(), JLabel.CENTER);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton btnRegistrarInspeccion = new JButton("Registrar nueva inspección");
        JButton btnAsociarCultivosPlagas = new JButton("Asociar cultivos y plagas");
        JButton btnObservaciones = new JButton("Ingresar observaciones");
        JButton btnConsultar = new JButton("Consultar mis inspecciones");
        JButton btnSalir = new JButton("Cerrar sesión");

        panel.add(lblBienvenida);
        panel.add(btnRegistrarInspeccion);
        panel.add(btnAsociarCultivosPlagas);
        panel.add(btnObservaciones);
        panel.add(btnConsultar);

        add(panel, BorderLayout.CENTER);
        add(btnSalir, BorderLayout.SOUTH);

        // Eventos de botones
        btnRegistrarInspeccion.addActionListener(e -> {
        new RegistrarInspeccionFrame((Tecnico) UserDatabase.getCurrentUser()).setVisible(true);
        });

        btnAsociarCultivosPlagas.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "🌱 Asociar cultivos y plagas (en construcción)");
        });

        btnObservaciones.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "📝 Ingresar observaciones (en construcción)");
        });

        btnConsultar.addActionListener(e -> {
         new ConsultarInspeccionesFrame(tecnico).setVisible(true);
        });

        btnSalir.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}
