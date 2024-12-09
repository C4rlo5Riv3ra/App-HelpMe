package com.example.helpme_app.Model;

import java.io.Serializable;

public class Estudiante  implements Serializable {
    private int usuario;
    private String universidad;
    private String carrera;
    private int nivelstudios;
    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getNivelstudios() {
        return nivelstudios;
    }

    public void setNivelstudios(int nivelstudios) {
        this.nivelstudios = nivelstudios;
    }


}
