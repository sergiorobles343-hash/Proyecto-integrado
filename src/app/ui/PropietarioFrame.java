/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ui;

/**
 *
 * @author sergi
 */
import app.model.Propietario;

import javax.swing.*;
import java.awt.*;

public class PropietarioFrame extends JFrame {
    private Propietario propietario;

    public PropietarioFrame(Propietario propietario) {
        this.propietario = propietario;

        setTitle("Panel del Propietario");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel lblTitulo = new JLabel("Bienvenido, " + propietario.getNombreCompleto(), JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 102, 204));
        panel.add(lblTitulo, BorderLayout.NORTH);

        JTextArea area = new JTextArea("Este será el panel del propietario.\nAquí podrás gestionar predios, ver inspecciones, etc.");
        area.setEditable(false);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnSalir = new JButton("Cerrar sesión");
        btnSalir.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
        panel.add(btnSalir, BorderLayout.SOUTH);

        add(panel);
    }
}