package com.adt.actividad.controller;


import com.adt.actividad.model.Alumno;
import com.adt.actividad.service.AlumnoService;
import com.adt.actividad.util.ConexionBD;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private AlumnoService alumnoService;

    public AlumnoController() {
        // TODO: Crear conexión manualmente (sin @Autowired)
        Connection conexion = ConexionBD.conectar();
        this.alumnoService = new AlumnoService(conexion);
    }

    // GET - Listar todos los Alumnos
    @GetMapping
    public List<Alumno> listarAlumnos() {
        return alumnoService.listarAlumnos();
    }

    // GET - Buscar Alumno por ID
    @GetMapping("/{id}")
    public Alumno listarAlumnoPorId(@PathVariable int id) {
        return alumnoService.listarAlumnoPorId(id);
    }

    // POST - Insertar nuevo Alumno
    @PostMapping
    public String insertar(@RequestBody Alumno alumno) {
        alumnoService.insertarAlumno(alumno);
        return "✅ Alumno creada correctamente.";
    }

    // PUT - Actualizar Alumno existente
    @PutMapping("/{id}")
    public String actualizarAlumno(@PathVariable int id, @RequestBody Alumno alumno) {
        alumno.setId_alumno(id);
        alumnoService.actualizarAlumno(alumno);
        return "✅ Alumno actualizada correctamente.";
    }

    // DELETE - Eliminar Alumno
    @DeleteMapping("/{id}")
    public String eliminarAlumno(@PathVariable int id) {
        alumnoService.eliminarAlumno(id);
        return "✅ Alumno eliminada correctamente.";
    }
}
