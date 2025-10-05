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
import app.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField txtNombre, txtDocumento, txtCorreo, txtUsuario;
    private JPasswordField txtContrasena, txtConfirmar;
    private JComboBox<String> cmbRol;
    private JButton btnRegistrar, btnCancelar;

    public RegisterFrame() {
        setTitle("Registro de Usuario - Sistema Fitosanitario");
        setSize(450, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Registro de Usuario", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;

        // Usuario
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Usuario:"), gbc);
        txtUsuario = new JTextField();
        gbc.gridx = 1;
        panel.add(txtUsuario, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField();
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);

        // Documento
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Documento:"), gbc);
        txtDocumento = new JTextField();
        gbc.gridx = 1;
        panel.add(txtDocumento, gbc);

        // Correo
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Correo:"), gbc);
        txtCorreo = new JTextField();
        gbc.gridx = 1;
        panel.add(txtCorreo, gbc);

        // Contraseña
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Contraseña:"), gbc);
        txtContrasena = new JPasswordField();
        gbc.gridx = 1;
        panel.add(txtContrasena, gbc);

        // Confirmar
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Confirmar:"), gbc);
        txtConfirmar = new JPasswordField();
        gbc.gridx = 1;
        panel.add(txtConfirmar, gbc);

        // Rol
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Rol:"), gbc);
        cmbRol = new JComboBox<>(new String[]{"Productor", "Propietario", "Tecnico"});
        gbc.gridx = 1;
        panel.add(cmbRol, gbc);

        // Botones
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(0, 153, 0));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setOpaque(true);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(204, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setOpaque(true);

        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(btnRegistrar, gbc);
        gbc.gridx = 1;
        panel.add(btnCancelar, gbc);

        // Botón de login
        JButton btnLogin = new JButton("¿Ya tienes cuenta? Inicia sesión");
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setOpaque(true);
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        add(panel);

        // Acción registrar
        btnRegistrar.addActionListener(e -> registrar());

        // Acción cancelar
        btnCancelar.addActionListener(e -> {
            txtUsuario.setText("");
            txtNombre.setText("");
            txtDocumento.setText("");
            txtCorreo.setText("");
            txtContrasena.setText("");
            txtConfirmar.setText("");
        });

        // Volver a login
        btnLogin.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }

    private void registrar() {
        String usuario = txtUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());
        String confirmar = new String(txtConfirmar.getPassword());
        String rol = (String) cmbRol.getSelectedItem();

        if (usuario.isEmpty() || nombre.isEmpty() || documento.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Todos los campos son obligatorios");
            return;
        }
        if (!contrasena.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "❌ Las contraseñas no coinciden");
            return;
        }

        // Ojo: ahora guardamos el "usuario" como ID, no el correo
        Usuario u = new Usuario(usuario, nombre, "", "", correo, contrasena, rol) {};
        boolean registrado = UserDatabase.registrarUsuario(u);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "✅ Usuario registrado con éxito");
            new LoginFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "⚠ El usuario ya existe");
        }
    }
}
