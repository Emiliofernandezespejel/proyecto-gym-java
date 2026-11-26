package dao;

import db.ConexionBD;
import modelo.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Consultas extra de la tabla socio: listar todos los socios
 * y sumarle puntos a un socio.
 */
public class SocioConsultaDAO {

    /**
     * Trae todos los socios registrados, ordenados por id.
     *
     * @return lista de socios (vacía si no hay o si hubo error)
     */
    public List<Socio> listarSocios() {
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socio ORDER BY id_socio";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            if (conn == null) {
                return lista;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Socio s = new Socio();
                    s.setIdSocio(rs.getInt("id_socio"));
                    s.setNombre(rs.getString("nombre"));
                    s.setNivel(rs.getString("nivel"));
                    s.setPuntajeAcumulado(rs.getInt("puntaje_acumulado"));
                    lista.add(s);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar socios: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Pasa todos los socios a un arreglo (opción b del menú).
     *
     * @return arreglo con todos los socios
     */
    public Socio[] obtenerArregloSocios() {
        return listarSocios().toArray(new Socio[0]);
    }

    /**
     * Le suma calorías (puntos) al puntaje acumulado de un socio.
     * Se usa cuando se procesa una sesión.
     *
     * @param idSocio  id del socio
     * @param calorias calorías de la sesión (mayor a 0)
     * @return true si se actualizó, false si no existe el socio o hubo error
     */
    public boolean sumarPuntaje(int idSocio, int calorias) {
        if (calorias <= 0) {
            System.err.println("Las calorias deben ser mayores a 0.");
            return false;
        }

        String sql = "UPDATE socio SET puntaje_acumulado = puntaje_acumulado + ? "
                   + "WHERE id_socio = ?";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            if (conn == null) {
                return false;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, calorias);
                pstmt.setInt(2, idSocio);
                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar puntaje: " + e.getMessage());
            return false;
        }
    }
}