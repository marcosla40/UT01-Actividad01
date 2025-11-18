package com.adt.actividad.model;

import java.util.Date;

public class Practica {
    private int idPractica;
    private Alumno alumno; // Asociación 1:N
    private Empresa empresa; // Asociación 1:N
    private Date fechaInicio;
    private Date fechaFin;
    private String estado; //"activa", "finalizada", "cancelada"

    public Practica(int idPractica, Alumno alumno, Empresa empresa, Date fechaInicio, Date fechaFin, String estado) {
        this.idPractica = idPractica;
        this.alumno = alumno;
        this.empresa = empresa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getIdPractica() {
        return idPractica;
    }

    public void setIdPractica(int idPractica) {
        this.idPractica = idPractica;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
