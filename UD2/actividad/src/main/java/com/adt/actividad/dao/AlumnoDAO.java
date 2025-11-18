package com.adt.actividad.dao;

import com.adt.actividad.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    private Connection conexion;

    public AlumnoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> listAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int id_alumno = rs.getInt("id_alumno");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String email = rs.getString("email");
            String curso = rs.getString("curso");

            Alumno alumno = new Alumno(id_alumno,nombre,apellidos,email,curso);
            listAlumnos.add(alumno);
        }
        return listAlumnos;
    }

    public Alumno listarAlumnoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM alumno WHERE id_alumno = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        Alumno alumno = null;

        while (rs.next()){
            int id_alumno = rs.getInt("id_alumno");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String email = rs.getString("email");
            String curso = rs.getString("curso");

            alumno = new Alumno(id_alumno,nombre,apellidos, email, curso);
        }
        return alumno;

    }

    public void insertarAlumno(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO alumno SET nombre = ?, apellidos = ?, email = ?, curso = ?";

        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setString(1,alumno.getNombre());
        ps.setString(2,alumno.getApellidos());
        ps.setString(3,alumno.getEmail());
        ps.setString(4,alumno.getCurso());

        ps.executeUpdate();
    }

    public void actualizarAlumno(Alumno alumno) throws SQLException {
        String sql = "UPDATE alumno SET nombre = ?, apellidos = ?, email = ?, curso = ? WHERE id_alumno = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, alumno.getNombre());
        ps.setString(2,alumno.getApellidos());
        ps.setString(3,alumno.getEmail());
        ps.setString(4,alumno.getCurso());
        ps.setInt(5,alumno.getId_alumno());

        ps.executeUpdate();
    }

    public void eliminarAlumno(int id) throws SQLException {
        String sql = "DELETE FROM alumno WHERE id_alumno = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1,id);

        ps.executeUpdate();
    }


}
