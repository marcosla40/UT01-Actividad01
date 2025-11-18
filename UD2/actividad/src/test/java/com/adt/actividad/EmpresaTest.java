package com.adt.actividad;

import com.adt.actividad.dao.EmpresaDAO;
import com.adt.actividad.model.Empresa;
import com.adt.actividad.util.ConexionBD;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class EmpresaTest {

    private Connection conexion;
    private EmpresaDAO empresaDAO;

    @BeforeEach
    public void setUp() throws Exception {
        conexion = ConexionBD.conectar();
        empresaDAO = new EmpresaDAO(conexion);

        Statement st = conexion.createStatement();

        // Limpiar tabla
        st.executeUpdate("DELETE FROM empresa");

        // Reiniciar autoincrement
        st.executeUpdate("ALTER TABLE empresa AUTO_INCREMENT = 1");
    }

    @Test
    public void testInsertarEmpresas() throws Exception {
        empresaDAO.insertarEmpresa(new Empresa(0, "Google", "software", "1", "g@mail.com", "USA"));
        empresaDAO.insertarEmpresa(new Empresa(0, "Microsoft", "multiplaforma", "2", "m@mail.com", "USA"));
        empresaDAO.insertarEmpresa(new Empresa(0, "Netlify", "web", "3", "n@mail.com", "USA"));

        List<Empresa> lista = empresaDAO.listarEmpresas();

        assertEquals(3, lista.size());
    }

    @Test
    public void testGetNumEmpresas() throws Exception {
        empresaDAO.insertarEmpresa(new Empresa(0, "A", "Tec", "1", "a@mail.com", "C1"));
        empresaDAO.insertarEmpresa(new Empresa(0, "B", "Tec", "2", "b@mail.com", "C2"));
        empresaDAO.insertarEmpresa(new Empresa(0, "C", "Tec", "3", "c@mail.com", "C3"));

        PreparedStatement ps = conexion.prepareStatement("SELECT COUNT(*) FROM empresa");
        ResultSet rs = ps.executeQuery();
        rs.next();

        int total = rs.getInt(1);

        assertEquals(3, total);
    }

    @Test
    public void testEmpresasPorSector() throws Exception {
        empresaDAO.insertarEmpresa(new Empresa(0, "Google", "software", "1", "g@mail.com", "USA"));
        empresaDAO.insertarEmpresa(new Empresa(0, "Microsoft", "software", "2", "m@mail.com", "USA"));
        empresaDAO.insertarEmpresa(new Empresa(0, "Amazon", "ecommerce", "3", "a@mail.com", "USA"));

        List<Empresa> sectorSoftware = empresaDAO.buscarPorSector("software");

        assertEquals(2, sectorSoftware.size());
        assertEquals("Google", sectorSoftware.get(0).getNombre());
        assertEquals("Microsoft", sectorSoftware.get(1).getNombre());
    }

    @Test
    public void testListarEmpresas() throws Exception {
        empresaDAO.insertarEmpresa(new Empresa(0, "A", "Tec", "1", "a@mail.com", "C1"));
        empresaDAO.insertarEmpresa(new Empresa(0, "B", "Tec", "2", "b@mail.com", "C2"));

        List<Empresa> lista = empresaDAO.listarEmpresas();

        assertEquals(2, lista.size());
    }

    /*********************************************************************/

    @Test
    public void testListarEmpresaPorId() throws Exception {
        Empresa e = new Empresa(0, "Apple", "Software", "99999", "apple@mail.com", "Cupertino");
        empresaDAO.insertarEmpresa(e);

        Empresa recuperada = empresaDAO.listarEmpresas().get(0);

        Empresa buscada = empresaDAO.listarEmpresaPorId(recuperada.getId_empresa());

        assertNotNull(buscada);
        assertEquals("Apple", buscada.getNombre());
    }

    @Test
    public void testInsertarEmpresa() throws Exception {
        Empresa e = new Empresa(0, "Google", "Software", "12345", "google@mail.com", "California");

        empresaDAO.insertarEmpresa(e);

        List<Empresa> lista = empresaDAO.listarEmpresas();

        assertEquals(1, lista.size());
        assertEquals("Google", lista.get(0).getNombre());
    }

    @Test
    public void testActualizarEmpresa() throws Exception {
        Empresa e = new Empresa(0, "Meta", "Software", "11111", "meta@mail.com", "California");
        empresaDAO.insertarEmpresa(e);

        Empresa recuperada = empresaDAO.listarEmpresas().get(0);
        recuperada.setEmail("nuevo@mail.com");

        empresaDAO.actualizarEmpresa(recuperada);

        Empresa actualizada = empresaDAO.listarEmpresaPorId(recuperada.getId_empresa());

        assertEquals("nuevo@mail.com", actualizada.getEmail());
    }

    @Test
    public void testEliminarEmpresa() throws Exception {
        Empresa e = new Empresa(0, "IBM", "Software", "1111", "ibm@mail.com", "USA");
        empresaDAO.insertarEmpresa(e);

        int id = empresaDAO.listarEmpresas().get(0).getId_empresa();

        empresaDAO.eliminarEmpresa(id);

        Empresa resultado = empresaDAO.listarEmpresaPorId(id);
        assertNull(resultado);
    }

}