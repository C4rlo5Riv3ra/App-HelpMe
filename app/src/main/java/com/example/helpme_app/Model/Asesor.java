package com.example.helpme_app.Model;

import java.io.Serializable;

public class Asesor implements Serializable {
    private int aniosExperiencia;
    private String especialidad;
    private String presentacion;
    private String enseniazaPreferida;
    private String codigoColegiatura;

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getCodigoColegiatura() {
        return codigoColegiatura;
    }

    public void setCodigoColegiatura(String codigoColegiatura) {
        this.codigoColegiatura = codigoColegiatura;
    }

    public String getEnseniazaPreferida() {
        return enseniazaPreferida;
    }

    public void setEnseniazaPreferida(String enseniazaPreferida) {
        this.enseniazaPreferida = enseniazaPreferida;
    }
}
