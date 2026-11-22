package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/gimnasio_db?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String PASSWORD = "Emiliofac05";


    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión a MYSQL exitosa.");
        } catch (ClassNotFoundException e) {
            System.err.println("Eror: Driver de MySQL no encontrado. Revise la carpeta lib.");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return conexion;

    }
    
}
