package repository;

import model.Usuario;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación JDBC del repositorio de Usuario.
 * Usa PreparedStatement y try-with-resources.
 */
public class UsuarioRepository implements Repository<Usuario, Integer> {

    private final Connection conn;

    public UsuarioRepository() throws SQLException {
        this.conn = ConexionMySQL.getInstance().getConnection();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String sql = "SELECT id_usuario, username, password_hash, clave_aes FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando usuario por id", e);
        }
        return Optional.empty();
    }

    /**
     * Busca un usuario por su nombre de usuario.
     */
    public Optional<Usuario> findByUsername(String username) {
        String sql = "SELECT id_usuario, username, password_hash, clave_aes FROM usuarios WHERE username = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando usuario por username", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT id_usuario, username, password_hash, clave_aes FROM usuarios";
        List<Usuario> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando usuarios", e);
        }
        return list;
    }

    @Override
    public void save(Usuario u) {
        if (u.getIdUsuario() <= 0) {
            // Insert
            String sql = "INSERT INTO usuarios (username, password_hash, clave_aes) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, u.getUsername());
                ps.setString(2, u.getPasswordHash());
                ps.setString(3, u.getClaveAES());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        u.setIdUsuario(keys.getInt(1));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error insertando usuario", e);
            }
        } else {
            // Update
            String sql = "UPDATE usuarios SET username = ?, password_hash = ?, clave_aes = ? WHERE id_usuario = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, u.getUsername());
                ps.setString(2, u.getPasswordHash());
                ps.setString(3, u.getClaveAES());
                ps.setInt(4, u.getIdUsuario());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error actualizando usuario", e);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando usuario", e);
        }
    }

    private Usuario mapRow(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setUsername(rs.getString("username"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setClaveAES(rs.getString("clave_aes"));
        return u;
    }
}
