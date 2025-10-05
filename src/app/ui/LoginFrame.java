package app.ui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sergi
 */


import app.db.UserDatabase;
import app.model.Productor;
import app.model.Propietario;
import app.model.Tecnico;
import app.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin, btnRegistro;

    public LoginFrame() {
        setTitle("Inicio de Sesión - Sistema Fitosanitario");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fondo blanco y limpio
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.white);

        // Restricciones para el GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("Inicio de Sesión", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Campo de usuario
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Usuario:"), gbc);
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        panel.add(txtUsuario, gbc);

        // Campo de contraseña
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);
        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        panel.add(txtContrasena, gbc);

        // Botones
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 153, 76));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnRegistro = new JButton("Registrarse");
        btnRegistro.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistro.setBackground(new Color(0, 102, 204));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setFocusPainted(false);
        btnRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Añadir los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.white);
        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistro);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        // Agregar el panel al frame
        add(panel);

        // Acción de login
        btnLogin.addActionListener(e -> login());

        // Acción para abrir registro
        btnRegistro.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            dispose();
        });
    }

    private void login() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        String rol = UserDatabase.validarUsuario(usuario, contrasena);

        if (rol == null) {
            JOptionPane.showMessageDialog(this, "❌ Usuario o contraseña incorrectos");
            return;
        }

        // Abrir ventana según el rol
switch (rol) {
    case "Productor":
        new ProductorFrame(
            new Productor(usuario, usuario, "", "", usuario + "@mail.com", "Café", contrasena)
        ).setVisible(true);
        break;

   case "Propietario":
    new PropietarioFrame(
        new Propietario(usuario, usuario, "", "", usuario + "@mail.com", contrasena)
    ).setVisible(true);
    break;

    case "Tecnico":
        new TecnicoFrame(
            new Tecnico(usuario, usuario, "", "", usuario + "@mail.com", contrasena)
        ).setVisible(true);
        break;

    default:
        JOptionPane.showMessageDialog(this, "⚠ Rol desconocido");
        return;
}
dispose();


    }
}
