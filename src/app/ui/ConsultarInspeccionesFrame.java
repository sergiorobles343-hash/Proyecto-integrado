/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ui;

/**
 *
 * @author sergi
 */
import app.db.InspeccionDatabase;
import app.model.Inspeccion;
import app.model.Tecnico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ConsultarInspeccionesFrame extends JFrame {

    private JTable tablaInspecciones;
    private DefaultTableModel modeloTabla;

    public ConsultarInspeccionesFrame(Tecnico tecnico) {
        setTitle("Mis Inspecciones");
        setSize(700, 400);
        setLocationRelativeTo(null);

        // Definimos columnas
        modeloTabla = new DefaultTableModel(
                new Object[]{"Predio", "Fecha", "Cultivo", "Plaga", "Observaciones"},
                0
        );
        tablaInspecciones = new JTable(modeloTabla);

        // Cargar inspecciones de este técnico
        cargarInspecciones(tecnico.getId());

        add(new JScrollPane(tablaInspecciones), BorderLayout.CENTER);
    }

    private void cargarInspecciones(String tecnicoId) {
        List<Inspeccion> lista = InspeccionDatabase.getInspeccionesPorTecnico(tecnicoId);

        modeloTabla.setRowCount(0); // limpiar
        for (Inspeccion i : lista) {
            modeloTabla.addRow(new Object[]{
                    i.getPredio(),
                    i.getFecha(),
                    i.getCultivo(),
                    i.getPlaga(),
                    i.getObservaciones()
            });
        }
    }
}