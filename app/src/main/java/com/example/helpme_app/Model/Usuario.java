package com.example.helpme_app.Model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idUsuario;
    private String email;
    private String password;
    private String rol; // Enum para el campo rol
    private int persona;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    private String biografia;
    private byte[] fotoprofile; // Usamos byte[] para almacenar el longblob
    private int tokens;

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario(float saldo, int tokens, byte[] fotoprofile, String biografia, int persona, String rol, String password, String email, int idUsuario) {
        this.saldo = saldo;
        this.tokens = tokens;
        this.fotoprofile = fotoprofile;
        this.biografia = biografia;
        this.persona = persona;
        this.rol = rol;
        this.password = password;
        this.email = email;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public byte[] getFotoprofile() {
        return fotoprofile;
    }

    public void setFotoprofile(byte[] fotoprofile) {
        this.fotoprofile = fotoprofile;
    }

    private float saldo;

    public Usuario() {
        // Constructor vacío para inicialización sin parámetros
    }
}
