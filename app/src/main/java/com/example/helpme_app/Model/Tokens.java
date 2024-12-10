package com.example.helpme_app.Model;

import java.io.Serializable;

public class Tokens implements Serializable {
    private String tipoAsesor;
    private int tokens;
    private int horas;

    public String getTipoAsesor() {
        return tipoAsesor;
    }

    public void setTipoAsesor(String tipoAsesor) {
        this.tipoAsesor = tipoAsesor;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
