package com.example.helpme_app.Model.Estudiantes;

public class EstudianteResponse {
    private String data[];
    private String menssage;
    private int status;

    public EstudianteResponse(String[] data, String menssage, int status) {
        this.data = data;
        this.menssage = menssage;
        this.status = status;
    }

    public EstudianteResponse() {
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
