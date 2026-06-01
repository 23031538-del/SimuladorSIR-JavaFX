package repository;

import model.Credencial;
import Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación JDBC del repositorio de Credencial.
 */
public class CredencialRepository implements Repository<Credencial, Integer> {

    private final Connection conn;

    public CredencialRepository() throws SQLException {
        this.conn = ConexionMySQL.getInstance().getConnection();
    }

    @Override
    public Optional<Credencial> findById(Integer id) {
        String sql = "SELECT id_credencial, id_usuario, sitio, correo, password_cifrado, notas FROM credenciales WHERE id_credencial = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando credencial por id", e);
        }
        return Optional.empty();
    }

    /**
     * Lista todas las credenciales asociadas a un usuario.
     */
    public List<Credencial> findByUsuarioId(int idUsuario) {
        String sql = "SELECT id_credencial, id_usuario, sitio, correo, password_cifrado, notas FROM credenciales WHERE id_usuario = ?";
        List<Credencial> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando credenciales por usuario", e);
        }
        return list;
    }

    @Override
    public List<Credencial> findAll() {
        String sql = "SELECT id_credencial, id_usuario, sitio, correo, password_cifrado, notas FROM credenciales";
        List<Credencial> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando credenciales", e);
        }
        return list;
    }

    @Override
    public void save(Credencial c) {
        if (c.getIdCredencial() <= 0) {
            String sql = "INSERT INTO credenciales (id_usuario, sitio, correo, password_cifrado, notas) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, c.getIdUsuario());
                ps.setString(2, c.getSitio());
                ps.setString(3, c.getCorreo());
                ps.setString(4, c.getPasswordCifrado());
                ps.setString(5, c.getNotas());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        c.setIdCredencial(keys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error insertando credencial", e);
            }
        } else {
            String sql = "UPDATE credenciales SET id_usuario = ?, sitio = ?, correo = ?, password_cifrado = ?, notas = ? WHERE id_credencial = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, c.getIdUsuario());
                ps.setString(2, c.getSitio());
                ps.setString(3, c.getCorreo());
                ps.setString(4, c.getPasswordCifrado());
                ps.setString(5, c.getNotas());
                ps.setInt(6, c.getIdCredencial());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error actualizando credencial", e);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM credenciales WHERE id_credencial = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando credencial", e);
        }
    }

    private Credencial mapRow(ResultSet rs) throws SQLException {
        Credencial c = new Credencial();
        c.setIdCredencial(rs.getInt("id_credencial"));
        c.setIdUsuario(rs.getInt("id_usuario"));
        c.setSitio(rs.getString("sitio"));
        c.setCorreo(rs.getString("correo"));
        c.setPasswordCifrado(rs.getString("password_cifrado"));
        c.setNotas(rs.getString("notas"));
        return c;
    }
}