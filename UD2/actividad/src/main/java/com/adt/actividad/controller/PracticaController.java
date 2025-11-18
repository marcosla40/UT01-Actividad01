package com.adt.actividad.controller;

import com.adt.actividad.model.Practica;
import com.adt.actividad.service.PracticaService;
import com.adt.actividad.util.ConexionBD;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/practicas")
public class PracticaController {

    private PracticaService practicaService;

    public PracticaController() {
        // TODO: Crear conexión manualmente (sin @Autowired)
        Connection conexion = ConexionBD.conectar();
        this.practicaService = new PracticaService(conexion);
    }

    // GET - Listar todas las practicas
    @GetMapping
    public List<Practica> listarPracticas() {
        return practicaService.listarPracticas();
    }


    // GET - Buscar practica por ID
    @GetMapping("/{id}")
    public Practica listarPracticaPorId(@PathVariable int id) {
        return practicaService.listarPracticaPorId(id);
    }

    // POST - Insertar nueva practica
    @PostMapping
    public String insertar(@RequestBody Practica practica) {
        practicaService.insertarPractica(practica);
        return "✅ Practica creada correctamente.";
    }

    // PUT - Actualizar practica existente
    @PutMapping("/{id}")
    public String actualizar(@PathVariable int id, @RequestBody Practica practica) {
        practica.setIdPractica(id);
        practicaService.actualizarPracticas(practica);
        return "✅ Practica actualizada correctamente.";
    }

    // DELETE - Eliminar practica
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        practicaService.eliminarPractica(id);
        return "✅ Practica eliminada correctamente.";
    }
}
