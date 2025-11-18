package com.adt.actividad.service;

import com.adt.actividad.dao.AlumnoDAO;
import com.adt.actividad.model.Alumno;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AlumnoService {
    private AlumnoDAO alumnoDAO;
    // Inicializar el DAO recibiendo la conexión
    public AlumnoService(Connection conexion) {
        this.alumnoDAO = new AlumnoDAO(conexion);
    }

    // listar todos los alumnos
    public List<Alumno> listarAlumnos() {
        try {
            return alumnoDAO.listarAlumnos();
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar alumnos", e);
        }
    }
    // Buscar alumno por ID
    public Alumno listarAlumnoPorId(int id) {
        try {
            return alumnoDAO.listarAlumnoPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar alumno", e);
        }
    }
    //TODO: Insertar nueva alumno
    public void insertarAlumno(Alumno alumno) {
        try {
            alumnoDAO.insertarAlumno(alumno);
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar alumno", e);
        }
    }
    //TODO: Actualizar alumno existente
    public void actualizarAlumno(Alumno alumno) {
        try {
            alumnoDAO.actualizarAlumno(alumno);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar alumno", e);
        }
    }
    // Eliminar alumno
    public void eliminarAlumno(int id) {
        try {
            alumnoDAO.eliminarAlumno(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar alumno", e);
        }
    }
}
