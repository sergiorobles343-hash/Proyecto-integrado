package app.db;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sergi
 */

import app.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static final List<Usuario> usuarios = new ArrayList<>();

    // Registrar usuario en memoria
    public static boolean registrarUsuario(Usuario usuario) {
        // Evitar duplicados (correo único)
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(usuario.getCorreo())) {
                return false; // Ya existe
            }
        }
        usuarios.add(usuario);
        return true;
    }

    // Validar login (usuario + contraseña)
    public static String validarUsuario(String usuario, String contrasena) {
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(usuario) && u.getPassword().equals(contrasena)) {
                return u.getRol();
            }
        }
        return null;
    }

    // Opcional: listar todos
    public static List<Usuario> getUsuarios() {
        return usuarios;
    }


    private static Usuario currentUser = null;

    // Guardar el usuario que inició sesión
    public static void setCurrentUser(Usuario usuario) {
        currentUser = usuario;
    }

    // Obtener el usuario logueado actual
    public static Usuario getCurrentUser() {
        return currentUser;
    }
}