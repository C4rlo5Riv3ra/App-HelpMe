package com.example.helpme_app.Model;

public class Asesoria {
    private String nombreAsesoria;
    private String tokens;
    private String duracion;

    public Asesoria(String nombreAsesoria, String tokens, String duracion) {
        this.nombreAsesoria = nombreAsesoria;
        this.tokens = tokens;
        this.duracion = duracion;
    }

    // Getters y Setters
    public String getNombreAsesoria() {
        return nombreAsesoria;
    }

    public void setNombreAsesoria(String nombreAsesoria) {
        this.nombreAsesoria = nombreAsesoria;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
