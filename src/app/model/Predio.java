/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author sergi
 */
public class Predio {
    private String nombre;
    private String ubicacion;
    private double area;   // en hectáreas por ejemplo
    private String cultivo; // principal cultivo asociado

    // Constructor
    public Predio(String nombre, String ubicacion, double area, String cultivo) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.area = area;
        this.cultivo = cultivo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    @Override
    public String toString() {
        return "Predio{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", area=" + area +
                ", cultivo='" + cultivo + '\'' +
                '}';
    }
}