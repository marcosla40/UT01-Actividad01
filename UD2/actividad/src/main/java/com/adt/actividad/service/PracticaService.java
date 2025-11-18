package com.adt.actividad.service;

import com.adt.actividad.dao.PracticaDAO;
import com.adt.actividad.model.Practica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PracticaService {
    private PracticaDAO practicaDAO;
    // Inicializar el DAO recibiendo la conexión
    public PracticaService(Connection conexion) {
        this.practicaDAO = new PracticaDAO(conexion);
    }
    // listar todas las practicas
    public List<Practica> listarPracticas() {
        try {
            return practicaDAO.listarPractica();
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar practicas", e);
        }
    }

    // Buscar practica por ID
    public Practica listarPracticaPorId(int id) {
        try {
            return practicaDAO.listarPracticaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar practica", e);
        }
    }
    //TODO: Insertar nueva practica
    public void insertarPractica(Practica practica) {
        try {
            practicaDAO.insertarPractica(practica);
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar practica", e);
        }
    }
    //TODO: Actualizar practica existente
    public void actualizarPracticas(Practica practica) {
        try {
            practicaDAO.actualizarPractica(practica);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar practica", e);
        }
    }
    // Eliminar practica
    public void eliminarPractica(int id) {
        try {
            practicaDAO.eliminarPractica(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar practica", e);
        }
    }
}
