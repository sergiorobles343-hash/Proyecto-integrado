/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author sergi
 */

public class Tecnico extends Usuario {

    private String especialidad; // Atributo propio del técnico

    public Tecnico(String id, String nombreCompleto, String direccion, String telefono, String correo, String password) {
        super(id, nombreCompleto, direccion, telefono, correo, password, "Tecnico");
        this.especialidad = ""; // Inicializamos vacío por defecto
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        // Retornamos el nombre completo heredado de Usuario
        return super.getNombreCompleto();
    }
}