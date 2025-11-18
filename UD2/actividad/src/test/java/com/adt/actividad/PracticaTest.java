package com.adt.actividad;

import com.adt.actividad.dao.AlumnoDAO;
import com.adt.actividad.dao.EmpresaDAO;
import com.adt.actividad.dao.PracticaDAO;
import com.adt.actividad.model.Alumno;
import com.adt.actividad.model.Empresa;
import com.adt.actividad.model.Practica;
import com.adt.actividad.util.ConexionBD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PracticaTest {

    Connection conexion;
    PracticaDAO practicaDAO;
    AlumnoDAO alumnoDAO;
    EmpresaDAO empresaDAO;

    int idAlumnoTest;
    int idEmpresaTest;

    @BeforeEach
    public void setUp() throws Exception {

        conexion = ConexionBD.conectar();

        practicaDAO = new PracticaDAO(conexion);
        alumnoDAO = new AlumnoDAO(conexion);
        empresaDAO = new EmpresaDAO(conexion);

        Statement st = conexion.createStatement();

        // Limpiar tablas con FK correctamente
        st.executeUpdate("DELETE FROM practica");
        st.executeUpdate("DELETE FROM alumno");
        st.executeUpdate("DELETE FROM empresa");

        // Reset autoincrement
        st.executeUpdate("ALTER TABLE practica AUTO_INCREMENT = 1");
        st.executeUpdate("ALTER TABLE alumno AUTO_INCREMENT = 1");
        st.executeUpdate("ALTER TABLE empresa AUTO_INCREMENT = 1");

        // --- Insertar datos mínimos para las prácticas ---
        alumnoDAO.insertarAlumno(new Alumno(0, "Luis", "García", "luis@mail.com", "DAM2"));
        empresaDAO.insertarEmpresa(new Empresa(0, "Google", "Tecnología", "123", "google@mail.com", "USA"));

        // Obtener IDs generados
        idAlumnoTest = alumnoDAO.listarAlumnos().get(0).getId_alumno();
        idEmpresaTest = empresaDAO.listarEmpresas().get(0).getId_empresa();
    }

    @Test
    public void testInsertarPractica() throws Exception {

        // --- Preparación de datos: 2 alumnos y 2 empresas ---
        Alumno a1 = new Alumno(0, "Luis", "García", "luis@mail.com", "DAM2");
        Alumno a2 = new Alumno(0, "Ana", "Suarez", "ana@mail.com", "DAM2");

        alumnoDAO.insertarAlumno(a1);
        alumnoDAO.insertarAlumno(a2);

        // Recuperar con IDs autogenerados
        List<Alumno> alumnos = alumnoDAO.listarAlumnos();
        Alumno alumno1 = alumnos.get(0);
        Alumno alumno2 = alumnos.get(1);

        Empresa e1 = new Empresa(0, "Google", "Software", "111", "g@google.com", "California");
        Empresa e2 = new Empresa(0, "Amazon", "Ecommerce", "222", "a@amazon.com", "Seattle");

        empresaDAO.insertarEmpresa(e1);
        empresaDAO.insertarEmpresa(e2);

        // Recuperar IDs
        List<Empresa> empresas = empresaDAO.listarEmpresas();
        Empresa empresa1 = empresas.get(0);
        Empresa empresa2 = empresas.get(1);


        // --- Insertar prácticas FEBRERO ---
        Practica p1a = new Practica(
                0, alumno1, empresa1,
                Date.valueOf("2025-02-01"),
                Date.valueOf("2025-02-28"),
                "Activa"
        );

        Practica p2a = new Practica(
                0, alumno2, empresa1,
                Date.valueOf("2025-02-01"),
                Date.valueOf("2025-02-28"),
                "Activa"
        );

        practicaDAO.insertarPractica(p1a);
        practicaDAO.insertarPractica(p2a);


        // --- Insertar prácticas MARZO ---
        Practica p1b = new Practica(
                0, alumno1, empresa2,
                Date.valueOf("2025-03-01"),
                Date.valueOf("2025-03-31"),
                "Activa"
        );

        Practica p2b = new Practica(
                0, alumno2, empresa2,
                Date.valueOf("2025-03-01"),
                Date.valueOf("2025-03-31"),
                "Activa"
        );

        practicaDAO.insertarPractica(p1b);
        practicaDAO.insertarPractica(p2b);


        // --- Verificación ---
        List<Practica> practicas = practicaDAO.listarPractica();

        // 4 prácticas en total (2 alumnos × 2 periodos)
        assertEquals(4, practicas.size());

        // Comprobación opcional de meses
        assertTrue(practicas.stream().anyMatch(p -> p.getFechaInicio().toString().startsWith("2025-02")));
        assertTrue(practicas.stream().anyMatch(p -> p.getFechaInicio().toString().startsWith("2025-03")));
    }

    /*@Test
    public void testListarPracticasProcedure() throws Exception {

        PracticaDAO practicaDAO = new PracticaDAO(conexion);

        // Insertamos datos de prueba
        Practica p1 = new Practica(0, alumno1, empresa1,
                Date.valueOf("2025-02-01"), Date.valueOf("2025-02-15"), "Activa");

        Practica p2 = new Practica(0, alumno2, empresa2,
                Date.valueOf("2025-03-01"), Date.valueOf("2025-03-20"), "Activa");

        practicaDAO.insertarPractica(p1);
        practicaDAO.insertarPractica(p2);

        // Llamada al procedure
        List<Practica> lista = practicaDAO.listarPracticasProcedure();

        assertEquals(2, lista.size());

        // Verificación de datos concretos
        assertTrue(lista.stream().anyMatch(p -> p.getEstado().equals("Activa")));
    }*/

  /*  @Test
    public void testContarNumPracticasPorSector() throws Exception {

        PracticaDAO practicaDAO = new PracticaDAO(conexion);

        // Insertar prácticas en 2 sectores distintos
        Empresa software = new Empresa(0, "Google", "Software", "111", "g@gmail.com", "USA");
        Empresa comercio = new Empresa(0, "Amazon", "Comercio", "222", "a@gmail.com", "USA");

        empresaDAO.insertarEmpresa(software);
        empresaDAO.insertarEmpresa(comercio);

        software = empresaDAO.listarEmpresas().get(0);
        comercio = empresaDAO.listarEmpresas().get(1);

        Alumno a1 = new Alumno(0, "Luis", "García", "luis@mail.com", "DAM2");
        Alumno a2 = new Alumno(0, "Ana", "Suarez", "ana@mail.com", "DAM2");

        alumnoDAO.insertarAlumno(a1);
        alumnoDAO.insertarAlumno(a2);

        a1 = alumnoDAO.listarAlumnos().get(0);
        a2 = alumnoDAO.listarAlumnos().get(1);

        // Insertamos 3 prácticas: 2 en Software y 1 en Comercio
        practicaDAO.insertarPractica(new Practica(0, a1, software,
                Date.valueOf("2025-02-01"), Date.valueOf("2025-02-28"), "Activa"));

        practicaDAO.insertarPractica(new Practica(0, a2, software,
                Date.valueOf("2025-03-01"), Date.valueOf("2025-03-31"), "Activa"));

        practicaDAO.insertarPractica(new Practica(0, a1, comercio,
                Date.valueOf("2025-04-01"), Date.valueOf("2025-04-30"), "Activa"));

        // Llamada al procedure
        Map<String, Integer> mapa = practicaDAO.contarPracticasPorSector();

        // Comprobaciones
        assertEquals(2, mapa.get("Software"));
        assertEquals(1, mapa.get("Comercio"));
        assertEquals(2, mapa.size());
    }*/





    @Test
    public void testListarPracticas() throws Exception {
        // Insertar dos prácticas
        practicaDAO.insertarPractica(new Practica(
                0,
                alumnoDAO.listarAlumnoPorId(idAlumnoTest),
                empresaDAO.listarEmpresaPorId(idEmpresaTest),
                Date.valueOf("2025-01-10"),
                Date.valueOf("2025-02-10"),
                "Activa"
        ));

        practicaDAO.insertarPractica(new Practica(
                0,
                alumnoDAO.listarAlumnoPorId(idAlumnoTest),
                empresaDAO.listarEmpresaPorId(idEmpresaTest),
                Date.valueOf("2025-03-01"),
                Date.valueOf("2025-04-01"),
                "Finalizada"
        ));

        List<Practica> lista = practicaDAO.listarPractica();
        assertEquals(2, lista.size());
    }

    @Test
    public void testActualizarPractica() throws Exception {

        Practica p = new Practica(
                0,
                alumnoDAO.listarAlumnoPorId(idAlumnoTest),
                empresaDAO.listarEmpresaPorId(idEmpresaTest),
                Date.valueOf("2025-01-01"),
                Date.valueOf("2025-02-01"),
                "Activa"
        );

        practicaDAO.insertarPractica(p);

        Practica guardada = practicaDAO.listarPractica().get(0);

        // Cambiar datos
        guardada.setEstado("Finalizada");
        guardada.setFechaFin(Date.valueOf("2025-02-15"));

        practicaDAO.actualizarPractica(guardada);

        Practica actualizada = practicaDAO.listarPractica().get(0);

        assertEquals("Finalizada", actualizada.getEstado());
        assertEquals(Date.valueOf("2025-02-15"), actualizada.getFechaFin());
    }

    @Test
    public void testEliminarPractica() throws Exception {

        Practica p = new Practica(
                0,
                alumnoDAO.listarAlumnoPorId(idAlumnoTest),
                empresaDAO.listarEmpresaPorId(idEmpresaTest),
                Date.valueOf("2025-01-01"),
                Date.valueOf("2025-02-01"),
                "Activa"
        );

        practicaDAO.insertarPractica(p);

        int idPractica = practicaDAO.listarPractica().get(0).getIdPractica();

        practicaDAO.eliminarPractica(idPractica);

        List<Practica> lista = practicaDAO.listarPractica();

        assertEquals(0, lista.size());
    }

}

