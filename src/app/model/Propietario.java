/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author sergi
 */

public class Propietario extends Usuario {

    public Propietario(String id, String nombreCompleto, String direccion, String telefono, String correo, String password) {
        super(id, nombreCompleto, direccion, telefono, correo, password, "Propietario");
    }
}