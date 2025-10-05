/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author sergi
 */

import java.util.Date;

public class Inspeccion {
    private String predio;
    private Date fecha;
    private String tecnicoId;  // ID del técnico que realizó la inspección
    private String cultivo;
    private String plaga;
    private String observaciones;

    public Inspeccion(String predio, Date fecha, String tecnicoId, String cultivo, String plaga, String observaciones) {
        this.predio = predio;
        this.fecha = fecha;
        this.tecnicoId = tecnicoId;
        this.cultivo = cultivo;
        this.plaga = plaga;
        this.observaciones = observaciones;
    }

    public String getPredio() { return predio; }
    public Date getFecha() { return fecha; }
    public String getTecnicoId() { return tecnicoId; }
    public String getCultivo() { return cultivo; }
    public String getPlaga() { return plaga; }
    public String getObservaciones() { return observaciones; }

    public void setPredio(String predio) { this.predio = predio; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setTecnicoId(String tecnicoId) { this.tecnicoId = tecnicoId; }
    public void setCultivo(String cultivo) { this.cultivo = cultivo; }
    public void setPlaga(String plaga) { this.plaga = plaga; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}