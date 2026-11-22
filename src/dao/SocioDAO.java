package dao;

import db.ConexionBD;
import modelo.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO {

    public boolean registrarSocio(Socio socio) {
        String sql = "INSERT INTO socio (nombre, nivel, puntaje_acumulado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, socio.getNombre());
            pstmt.setString(2, socio.getNivel());
            pstmt.setInt(3, socio.getPuntajeAcumulado());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public List<Socio> buscarSocio(String criterio) {
        List<Socio> listaSocios = new ArrayList<>();
        String sql = "SELECT * FROM socio WHERE id_socio = ? OR nombre LIKE ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            int idBuscado = -1;
            try {
                idBuscado = Integer.parseInt(criterio); 
            } catch (NumberFormatException ignored) {} 

            pstmt.setInt(1, idBuscado);
            pstmt.setString(2, "%" + criterio + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Socio s = new Socio();
                s.setIdSocio(rs.getInt("id_socio"));
                s.setNombre(rs.getString("nombre"));
                s.setNivel(rs.getString("nivel"));
                s.setPuntajeAcumulado(rs.getInt("puntaje_acumulado"));
                listaSocios.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar: " + e.getMessage());
        }
        return listaSocios;
    }
}