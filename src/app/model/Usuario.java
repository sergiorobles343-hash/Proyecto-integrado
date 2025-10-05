/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author sergi
 */


public abstract class Usuario {
    private String id;
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    private String correo;
    private String password;
    private String rol;

    public Usuario(String id, String nombreCompleto, String direccion, String telefono,
                   String correo, String password, String rol) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    // Getters
    public String getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }

    // Setters
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setPassword(String password) { this.password = password; }
}
