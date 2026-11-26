package dao;

import db.ConexionBD;
import modelo.SesionEntrenamiento;
import modelo.Socio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Guarda sesiones en la base de datos y consulta las últimas de un socio.
 */
public class SesionDAO {

    /**
     * Guarda una sesión nueva y le pone su id.
     *
     * @param sesion sesión a guardar
     * @return true si se guardó, false si hubo error
     */
    public boolean registrarSesion(SesionEntrenamiento sesion) {
                // Evita NullPointerException si la sesión viene incompleta
        if (sesion == null || sesion.getSocio() == null
                || sesion.getEntrenador() == null || sesion.getFecha() == null) {
            System.err.println("La sesion esta incompleta.");
            return false;
        }
        String sql = "INSERT INTO sesion (id_socio, id_entrenador, puntos_obtenidos, fecha) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            if (conn == null) {
                return false;
            }
            try (PreparedStatement pstmt =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, sesion.getSocio().getIdSocio());
                pstmt.setInt(2, sesion.getEntrenador().getIdSocio());
                pstmt.setInt(3, sesion.getCalorias());
                pstmt.setDate(4, Date.valueOf(sesion.getFecha()));

                boolean guardada = pstmt.executeUpdate() > 0;
                if (guardada) {
                    // Saca el id que le puso la base de datos
                    try (ResultSet claves = pstmt.getGeneratedKeys()) {
                        if (claves.next()) {
                            sesion.setIdSesion(claves.getInt(1));
                        }
                    }
                }
                return guardada;
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar sesion: " + e.getMessage());
            return false;
        }
    }

    /**
     * Regresa las últimas 5 sesiones de un socio en una pila.
     * La más reciente queda hasta arriba.
     *
     * @param idSocio id del socio
     * @return pila con sus últimas sesiones (vacía si no tiene o hubo error)
     */
    public Stack<SesionEntrenamiento> ultimasSesiones(int idSocio) {
        Stack<SesionEntrenamiento> pila = new Stack<>();
        List<SesionEntrenamiento> recientes = new ArrayList<>();

        String sql = "SELECT se.id_sesion, se.puntos_obtenidos, se.fecha, "
                   + "s.id_socio AS id_s, s.nombre AS nombre_s, "
                   + "e.id_socio AS id_e, e.nombre AS nombre_e "
                   + "FROM sesion se "
                   + "JOIN socio s ON se.id_socio = s.id_socio "
                   + "JOIN socio e ON se.id_entrenador = e.id_socio "
                   + "WHERE se.id_socio = ? "
                   + "ORDER BY se.fecha DESC, se.id_sesion DESC LIMIT 5";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            if (conn == null) {
                return pila;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idSocio);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Socio socio = new Socio();
                        socio.setIdSocio(rs.getInt("id_s"));
                        socio.setNombre(rs.getString("nombre_s"));

                        Socio entrenador = new Socio();
                        entrenador.setIdSocio(rs.getInt("id_e"));
                        entrenador.setNombre(rs.getString("nombre_e"));

                        SesionEntrenamiento sesion = new SesionEntrenamiento(
                                socio, entrenador,
                                rs.getInt("puntos_obtenidos"),
                                rs.getDate("fecha").toLocalDate());
                        sesion.setIdSesion(rs.getInt("id_sesion"));
                        recientes.add(sesion);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar sesiones: " + e.getMessage());
        }

        // Se meten a la pila de la más vieja a la más nueva, así la más nueva queda arriba
        for (int i = recientes.size() - 1; i >= 0; i--) {
            pila.push(recientes.get(i));
        }
        return pila;
    }
}