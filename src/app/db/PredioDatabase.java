/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.db;

/**
 *
 * @author sergi
 */
import app.model.Predio;
import java.util.ArrayList;
import java.util.List;

public class PredioDatabase {
    // Lista que simula la base de datos
    private static List<Predio> predios = new ArrayList<>();

    // Agregar un nuevo predio
    public static void agregarPredio(Predio predio) {
        predios.add(predio);
    }

    // Obtener todos los predios
    public static List<Predio> getPredios() {
        return predios;
    }

    // Buscar predios por nombre (ejemplo simple)
    public static Predio buscarPredioPorNombre(String nombre) {
        for (Predio p : predios) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // Eliminar predio
    public static boolean eliminarPredio(String nombre) {
        Predio p = buscarPredioPorNombre(nombre);
        if (p != null) {
            predios.remove(p);
            return true;
        }
        return false;
    }

    public static List<Predio> obtenerPredios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}