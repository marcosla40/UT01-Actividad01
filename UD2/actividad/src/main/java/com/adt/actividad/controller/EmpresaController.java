package com.adt.actividad.controller;

import com.adt.actividad.model.Empresa;
import com.adt.actividad.service.EmpresaService;
import com.adt.actividad.util.ConexionBD;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private EmpresaService empresaService;

    public EmpresaController() {
        // TODO: Crear conexión manualmente (sin @Autowired)
        Connection conexion = ConexionBD.conectar();
        this.empresaService = new EmpresaService(conexion);
    }

    // GET - Listar todas las empresas
    @GetMapping
    public List<Empresa> listarEmpresa() {
        return empresaService.listarEmpresas();
    }

    // GET - Buscar empresa por ID
    @GetMapping("/{id}")
    public Empresa buscarPorId(@PathVariable int id) {
        return empresaService.listarEmpresaPorId(id);
    }

    // POST - Insertar nueva empresa
    @PostMapping
    public String insertarEmpresa(@RequestBody Empresa empresa) {
        empresaService.insertarEmpresa(empresa);
        return "✅ Empresa creada correctamente.";
    }

    // PUT - Actualizar empresa existente
    @PutMapping("/{id}")
    public String actualizarEmpresa(@PathVariable int id, @RequestBody Empresa empresa) {
        empresa.setId_empresa(id);
        empresaService.actualizarEmpresa(empresa);
        return "✅ Empresa actualizada correctamente.";
    }

    // DELETE - Eliminar empresa
    @DeleteMapping("/{id}")
    public String eliminarEmpresa(@PathVariable int id) {
        empresaService.eliminarEmpresa(id);
        return "✅ Empresa eliminada correctamente.";
    }
}
