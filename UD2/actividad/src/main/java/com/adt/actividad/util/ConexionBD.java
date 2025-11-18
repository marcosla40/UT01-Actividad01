package com.adt.actividad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // TODO: Cambiar el nombre de la base de datos según el proyecto
    private static final String URL = "jdbc:mysql://localhost:3306/adt-bd?serverTimezone=UTC";
    // TODO: Comprobar usuario y contraseña del servidor MySQL
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";

    // TODO: Crear método para conectar con la base de datos
    public static Connection conectar() {
        Connection conexion = null;
        try {
            // TODO: Registrar el driver JDBC de MySQL (opcional desde JDBC 4.0)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // TODO: Establecer conexión usando la clase DriverManager
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("✅ Conexión establecida correctamente.");
        } catch (ClassNotFoundException e) {
            // Manejar error cuando el driver no está disponible
            System.out.println("❌ Error: no se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Manejar error cuando falla la conexión (URL, usuario o contraseña incorrectos)
            System.out.println("❌ Error de conexión con la base de datos.");
            e.printStackTrace();
        }

        // TODO: Devolver el objeto Connection si se conecta correctamente
        return conexion;
    }
}
