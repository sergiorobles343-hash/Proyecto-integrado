/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author sergi
 */


import java.util.ArrayList;
import java.util.List;

public class Productor extends Usuario {
    private String tipoCultivo;
    private List<Predio> predios;

    public Productor(String id, String nombreCompleto, String direccion, String telefono,
                     String correo, String tipoCultivo, String password) {
        super(id, nombreCompleto, direccion, telefono, correo, password, "Productor");
        this.tipoCultivo = tipoCultivo;
        this.predios = new ArrayList<>();
    }

    public String getTipoCultivo() {
        return tipoCultivo;
    }

    // ✅ Ya no tira excepción, devuelve el nombre del usuario
    public String getNombre() {
        return getNombreCompleto(); 
    }

    // ✅ Ahora devuelve la lista real de predios
    public List<Predio> getPredios() {
        return predios;
    }

    // ✅ Método extra para poder agregar predios
    public void agregarPredio(Predio predio) {
        predios.add(predio);
    }
}
