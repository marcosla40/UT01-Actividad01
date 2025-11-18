package com.adt.actividad;

import com.adt.actividad.dao.AlumnoDAO;
import com.adt.actividad.model.Alumno;
import com.adt.actividad.util.ConexionBD;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class AlumnoTest {

    Connection conexion;
    AlumnoDAO alumnoDAO;

    @BeforeEach
    public void setUp() throws Exception {
        // 1. Obtener conexión
        conexion = ConexionBD.conectar();

        alumnoDAO = new AlumnoDAO(conexion);

        // 2. Limpiar tabla
        Statement st = conexion.createStatement();
        st.executeUpdate("DELETE FROM alumno");

        // 3. Resetear autoincrement
        st.executeUpdate("ALTER TABLE alumno AUTO_INCREMENT = 1");
    }

    @Test
    public void testInsertarAlumnos() throws Exception {
        Alumno a1 = new Alumno(0, "Luis", "García", "luis@correo.com", "DAM2");
        Alumno a2 = new Alumno(0, "Ana", "Suarez", "ana@correo.com", "DAM2");
        Alumno a3 = new Alumno(0, "Pedro", "López", "pedro@correo.com", "ASIR1");

        alumnoDAO.insertarAlumno(a1);
        alumnoDAO.insertarAlumno(a2);
        alumnoDAO.insertarAlumno(a3);

        List<Alumno> lista = alumnoDAO.listarAlumnos();

        assertEquals(3, lista.size());
    }

    @Test
    public void testGetNumAlumnos() throws SQLException {
        alumnoDAO.insertarAlumno(new Alumno(0, "Luis", "García", "a", "DAM"));
        alumnoDAO.insertarAlumno(new Alumno(0, "Ana", "Suarez", "a", "DAM"));

        String sql = "SELECT COUNT(*) FROM alumno";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        assertEquals(2, count);
    }

    @Test
    public void testActualizarAlumnos() throws Exception {
        Alumno a1 = new Alumno(0, "Luis", "García", "old@mail.com", "DAM2");
        alumnoDAO.insertarAlumno(a1);

        // Recuperar registro para obtener ID autogenerado
        Alumno recuperado = alumnoDAO.listarAlumnos().get(0);

        // Actualizar email
        String nuevoEmail = recuperado.getNombre() + "_" +
                recuperado.getApellidos() + "@educastur.org";
        recuperado.setEmail(nuevoEmail);

        alumnoDAO.actualizarAlumno(recuperado);

        Alumno actualizado = alumnoDAO.listarAlumnoPorId(recuperado.getId_alumno());

        assertEquals(nuevoEmail, actualizado.getEmail());
    }

    @Test
    public void testInsertarAlumno() throws Exception {
        Alumno alumno = new Alumno(0, "Juan", "Gonzales", "juan@gmail.com", "2DAM");
        alumnoDAO.insertarAlumno(alumno);

        List<Alumno> lista = alumnoDAO.listarAlumnos();
        assertEquals(1, lista.size());

        Alumno guardado = lista.get(0);
        assertEquals("Juan", guardado.getNombre());
        assertEquals("Gonzales", guardado.getApellidos());
    }

    /*--------------------------------------------------------*/

    @Test
    public void testListarAlumnos() throws Exception {
        alumnoDAO.insertarAlumno(new Alumno(0, "Luis", "Garcia", "a@a.com", "DAM"));
        alumnoDAO.insertarAlumno(new Alumno(0, "Ana", "Suarez", "b@b.com", "ASIR"));

        List<Alumno> lista = alumnoDAO.listarAlumnos();
        assertEquals(2, lista.size());
    }

    @Test
    public void testListarAlumnoPorId() throws Exception {
        Alumno alumno = new Alumno(0, "Luis", "Garcia", "luis@mail.com", "DAM");
        alumnoDAO.insertarAlumno(alumno);

        // obtener ID generado
        int id = alumnoDAO.listarAlumnos().get(0).getId_alumno();

        Alumno encontrado = alumnoDAO.listarAlumnoPorId(id);

        assertNotNull(encontrado);
        assertEquals("Luis", encontrado.getNombre());
    }

    @Test
    public void testActualizarAlumno() throws Exception {
        Alumno alumno = new Alumno(0, "Juan", "Perez", "old@mail.com", "DAM");
        alumnoDAO.insertarAlumno(alumno);

        Alumno guardado = alumnoDAO.listarAlumnos().get(0);
        guardado.setEmail("nuevo@mail.com");

        alumnoDAO.actualizarAlumno(guardado);

        Alumno actualizado = alumnoDAO.listarAlumnoPorId(guardado.getId_alumno());

        assertEquals("nuevo@mail.com", actualizado.getEmail());
    }

    @Test
    public void testEliminarAlumno() throws Exception {
        alumnoDAO.insertarAlumno(new Alumno(0, "Luis", "Garcia", "aaa@aaa.com", "DAM"));

        int id = alumnoDAO.listarAlumnos().get(0).getId_alumno();

        alumnoDAO.eliminarAlumno(id);

        Alumno eliminado = alumnoDAO.listarAlumnoPorId(id);
        assertNull(eliminado);
    }

}

