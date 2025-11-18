package com.adt.actividad.model;

public class Empresa {
    private int id_empresa;
    private String nombre;
    private String sector;
    private String telefono;
    private String email;
    private String direccion;

    public Empresa(int id_empresa, String nombre, String sector, String telefono, String email, String direccion) {
        this.id_empresa = id_empresa;
        this.nombre = nombre;
        this.sector = sector;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
