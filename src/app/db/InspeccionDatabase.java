/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.db;

/**
 *
 * @author sergi
 */
import app.model.Inspeccion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InspeccionDatabase {

    private static List<Inspeccion> inspecciones = new ArrayList<>();

    // Registrar una inspección
    public static void agregarInspeccion(Inspeccion inspeccion) {
        inspecciones.add(inspeccion);
    }

    // Obtener todas las inspecciones
    public static List<Inspeccion> getInspecciones() {
        return inspecciones;
    }

    // Obtener inspecciones realizadas por un técnico específico
    public static List<Inspeccion> getInspeccionesPorTecnico(String tecnicoId) {
        return inspecciones.stream()
                .filter(i -> i.getTecnicoId().equals(tecnicoId))
                .collect(Collectors.toList());
    }

    // Obtener inspecciones de un predio específico
    public static List<Inspeccion> obtenerInspeccionesPorPredio(String nombrePredio) {
        return inspecciones.stream()
                .filter(i -> i.getPredio().equalsIgnoreCase(nombrePredio))
                .collect(Collectors.toList());
    }
}