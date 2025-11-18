package com.adt.actividad.service;

import com.adt.actividad.dao.EmpresaDAO;
import com.adt.actividad.model.Empresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpresaService {
    private EmpresaDAO empresaDAO;
    // Inicializar el DAO recibiendo la conexión
    public EmpresaService(Connection conexion) {
        this.empresaDAO = new EmpresaDAO(conexion);
    }
    // listar todas las empresas
    public List<Empresa> listarEmpresas() {
        try {
            return empresaDAO.listarEmpresas();
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar empresas", e);
        }
    }
    // Buscar empresa por ID
    public Empresa listarEmpresaPorId(int id) {
        try {
            return empresaDAO.listarEmpresaPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar empresa", e);
        }
    }
    //TODO: Insertar nueva empresa
    public void insertarEmpresa(Empresa empresa) {
        try {
            empresaDAO.insertarEmpresa(empresa);
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar empresa", e);
        }
    }
    //TODO: Actualizar empresa existente
    public void actualizarEmpresa(Empresa empresa) {
        try {
            empresaDAO.actualizarEmpresa(empresa);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar empresa", e);
        }
    }
    // Eliminar empresa
    public void eliminarEmpresa(int id) {
        try {
            empresaDAO.eliminarEmpresa(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar empresa", e);
        }
    }
}
