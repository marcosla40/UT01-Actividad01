package com.adt.actividad.dao;

import com.adt.actividad.model.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {
    private Connection conexion;

    // Recibe la conexión de BD inicializada //
    public EmpresaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // TODO: Listar todas las empresas
    public List<Empresa> listarEmpresas() throws SQLException {
        List<Empresa> listaEmpresas = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);

        //recuperar el listado de empresas del ResultSet
        while (rs.next()) {
            int id_empresa = rs.getInt("id_empresa");
            String nombre = rs.getString("nombre");
            String sector = rs.getString("sector");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            String direccion = rs.getString("direccion");

            //crear la lista de empresas
            Empresa emp = new Empresa(id_empresa, nombre, sector, telefono, email, direccion);
            listaEmpresas.add(emp);
        }
        //devolvemos la lista de empresas
        return listaEmpresas;
    }

    // TODO: Buscar una empresa por su ID
    public Empresa listarEmpresaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM empresa WHERE id_empresa = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id); // primer parametro ?
        ResultSet rs = ps.executeQuery();

        Empresa emp = null;

        while (rs.next()) {
            int id_empresa = rs.getInt("id_empresa");
            String nombre = rs.getString("nombre");
            String sector = rs.getString("sector");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            String direccion = rs.getString("direccion");

            emp = new Empresa(id_empresa, nombre, sector, telefono, email, direccion);

        }
        return emp;
    }

    // TODO: Insertar una nueva empresa
    public void insertarEmpresa(Empresa empresa) throws SQLException {
        String sql = "INSERT INTO empresa (nombre, sector, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);


        ps.setString(1, empresa.getNombre());
        ps.setString(2, empresa.getSector());
        ps.setString(3, empresa.getTelefono());
        ps.setString(4, empresa.getEmail());
        ps.setString(5, empresa.getDireccion());

        ps.executeUpdate();
    }

    // TODO: Actualizar los datos de una empresa
    public void actualizarEmpresa(Empresa empresa) throws SQLException {
        String sql = "UPDATE empresa SET nombre = ?, sector = ?, telefono = ?, email = ?, direccion = ? WHERE id_empresa = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(1, empresa.getNombre());
        ps.setString(2, empresa.getSector());
        ps.setString(3, empresa.getTelefono());
        ps.setString(4, empresa.getEmail());
        ps.setString(5, empresa.getDireccion());
        ps.setInt(6, empresa.getId_empresa());

        ps.executeUpdate();

    }

    // TODO: Eliminar una empresa por su ID
    public void eliminarEmpresa(int id) throws SQLException {
        String sql = "DELETE FROM empresa WHERE id_empresa = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
    }

    // TODO: Buscar una empresa por su Sector
    public List<Empresa> buscarPorSector(String sectorEmp) throws SQLException {
        String sql = "SELECT * FROM empresa WHERE sector = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, sectorEmp); // primer parametro ?
        ResultSet rs = ps.executeQuery();
        List<Empresa> listEmpresas = new ArrayList<>();
        while (rs.next()) {
            int id_empresa = rs.getInt("id_empresa");
            String nombre = rs.getString("nombre");
            String sector = rs.getString("sector");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            String direccion = rs.getString("direccion");

            Empresa emp = new Empresa(id_empresa, nombre, sector, telefono, email, direccion);
            listEmpresas.add(emp);
        }
        return listEmpresas;
    }
}


