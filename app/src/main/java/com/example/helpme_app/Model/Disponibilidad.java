package com.example.helpme_app.Model;

import java.io.Serializable;
import java.util.Date;

public class Disponibilidad implements Serializable {

    private String disponibilidad[];
    private Date horaInicio;
    private Date horaFin;

    public String[] getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String[] disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
}
