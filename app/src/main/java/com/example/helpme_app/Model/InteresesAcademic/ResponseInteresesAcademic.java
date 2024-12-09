package com.example.helpme_app.Model.InteresesAcademic;

import java.util.List;

public class ResponseInteresesAcademic {
    private int status;
    private String message;
    private List<Interes> data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Interes> getData() {
        return data;
    }

    public static class Interes {
        private int id;
        private String nombre;

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }
    }
}
