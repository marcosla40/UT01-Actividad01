package com.adt.actividad.dao;

import com.adt.actividad.model.Alumno;
import com.adt.actividad.model.Empresa;
import com.adt.actividad.model.Practica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PracticaDAO {
    private Connection conexion;

    public PracticaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Practica> listarPractica() throws SQLException {
        List<Practica> listPracticas = new ArrayList<>();
        String sql = "select p.id_practica, p.fecha_inicio, p.fecha_fin, p.estado, " +
                "a.id_alumno, a.nombre as nombre_alumno, a.email as email_alumno, a.apellidos, a.curso, " +
                "e.id_empresa, e.nombre as nombre_empresa, e.sector, e.email as email_empresa, " +
                "e.direccion as direccion_empresa, e.telefono as telefono_empresa " +
                "from practica p " +
                "inner join alumno a on p.id_alumno = a.id_alumno " +
                "inner join empresa e on p.id_empresa = e.id_empresa " +
                "order by p.fecha_inicio desc";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Alumno a = new Alumno(
                    rs.getInt("a.id_alumno"),
                    rs.getString("nombre_alumno"),
                    rs.getString("a.apellidos"),
                    rs.getString("email_alumno"),
                    rs.getString("a.curso")
            );
            Empresa e = new Empresa(
                    rs.getInt("e.id_empresa"),
                    rs.getString("nombre_empresa"),
                    rs.getString("e.sector"),
                    rs.getString("telefono_empresa"),
                    rs.getString("email_empresa"),
                    rs.getString("direccion_empresa")
            );
            Practica practica = new Practica(
                    rs.getInt("p.id_practica"),
                    a,
                    e,
                    rs.getDate("p.fecha_inicio"),
                    rs.getDate("p.fecha_fin"),
                    rs.getString("p.estado")

            );
            listPracticas.add(practica);
        }
        return listPracticas;
    }

    public Practica listarPracticaPorId(int id) throws SQLException {
        Practica practica = null;
        String sql = "select p.id_practica, p.fecha_inicio, p.fecha_fin, p.estado, " +
                "a.id_alumno, a.nombre as nombre_alumno, a.email as email_alumno, a.apellidos, a.curso, " +
                "e.id_empresa, e.nombre as nombre_empresa, e.sector, e.email as email_empresa, " +
                "e.direccion as direccion_empresa, e.telefono as telefono_empresa " +
                "from practica p " +
                "inner join alumno a on p.id_alumno = a.id_alumno " +
                "inner join empresa e on p.id_empresa = e.id_empresa " +
                "WHERE id_alumno = ? order by p.fecha_inicio desc ";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Alumno a = new Alumno(
                    rs.getInt("a.id_alumno"),
                    rs.getString("nombre_alumno"),
                    rs.getString("a.apellidos"),
                    rs.getString("email_alumno"),
                    rs.getString("a.curso")
            );
            Empresa e = new Empresa(
                    rs.getInt("e.id_empresa"),
                    rs.getString("nombre_empresa"),
                    rs.getString("e.sector"),
                    rs.getString("telefono_empresa"),
                    rs.getString("email_empresa"),
                    rs.getString("direccion_empresa")
            );
            practica = new Practica(
                    rs.getInt("p.id_practica"),
                    a,
                    e,
                    rs.getDate("p.fecha_inicio"),
                    rs.getDate("p.fecha_fin"),
                    rs.getString("p.estado")

            );

        }
        return practica;
    }


    public void insertarPractica(Practica practica) throws SQLException {
        String sql = "INSERT INTO practica SET " +
                "fecha_inicio = ?, " +
                "fecha_fin = ?, " +
                "estado = ?, " +
                "id_alumno = ?, " +
                "id_empresa = ?";

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setDate(1, new Date(practica.getFechaInicio().getTime()));
        ps.setDate(2, new Date(practica.getFechaFin().getTime()));
        ps.setString(3, practica.getEstado());
        ps.setInt(4, practica.getAlumno().getId_alumno());
        ps.setInt(5, practica.getEmpresa().getId_empresa());

        ps.executeUpdate();
    }

    public void actualizarPractica(Practica practica) throws SQLException {
        String sql = "UPDATE practica SET " +
                "fecha_inicio = ?, " +
                "fecha_fin = ?, " +
                "estado = ?, " +
                "id_alumno = ?, " +
                "id_empresa = ? " +
                "WHERE id_practica = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setDate(1, new Date(practica.getFechaInicio().getTime()));
        ps.setDate(2, new Date(practica.getFechaFin().getTime()));
        ps.setString(3, practica.getEstado());
        ps.setInt(4, practica.getAlumno().getId_alumno());
        ps.setInt(5, practica.getEmpresa().getId_empresa());
        ps.setInt(6, practica.getIdPractica());

        ps.executeUpdate();
    }

    public void eliminarPractica(int id) throws SQLException {
        String sql = "DELETE FROM practica WHERE id_practica = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
    }

    public List<Practica> contarPracticasPorSector(String sector) throws SQLException {
        List<Practica> listPractica = new ArrayList<>();
        String sql = "";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, sector);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Alumno a = new Alumno(
                    rs.getInt("a.id_alumno"),
                    rs.getString("nombre_alumno"),
                    rs.getString("a.apellidos"),
                    rs.getString("email_alumno"),
                    rs.getString("a.curso")
            );
            Empresa e = new Empresa(
                    rs.getInt("e.id_empresa"),
                    rs.getString("nombre_empresa"),
                    rs.getString("e.sector"),
                    rs.getString("telefono_empresa"),
                    rs.getString("email_empresa"),
                    rs.getString("direccion_empresa")
            );
            Practica practica = new Practica(
                    rs.getInt("p.id_practica"),
                    a,
                    e,
                    rs.getDate("p.fecha_inicio"),
                    rs.getDate("p.fecha_fin"),
                    rs.getString("p.estado")

            );

            listPractica.add(practica);
        }
        return listPractica;
    }

}
/* public List<Practica> contarPracticasPorSector(String sector) throws SQLException {
        List<Practica> listPractica = new ArrayList<>();
        String sql = "";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, sector);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Alumno a = new Alumno(
                    rs.getInt("a.id_alumno"),
                    rs.getString("nombre_alumno"),
                    rs.getString("a.apellidos"),
                    rs.getString("email_alumno"),
                    rs.getString("a.curso")
            );
            Empresa e = new Empresa(
                    rs.getInt("e.id_empresa"),
                    rs.getString("nombre_empresa"),
                    rs.getString("e.sector"),
                    rs.getString("telefono_empresa"),
                    rs.getString("email_empresa"),
                    rs.getString("direccion_empresa")
            );
            Practica practica = new Practica(
                    rs.getInt("p.id_practica"),
                    a,
                    e,
                    rs.getDate("p.fecha_inicio"),
                    rs.getDate("p.fecha_fin"),
                    rs.getString("p.estado")

            );

            listPractica.add(practica);
        }
        return listPractica;
    }
Antes de hacer los test tengo que terminar este metodo del dao que cuenta cuantas practicas hay buscando por sector*/