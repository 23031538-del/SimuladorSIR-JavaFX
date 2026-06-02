package dao;

import database.ConexionMySQL;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private Connection conexion;

    public UsuarioDAOImpl() throws SQLException {
        conexion = ConexionMySQL.getInstance().getConnection();
    }

    @Override
    public void insertar(Usuario usuario) {

        String sql =
                "INSERT INTO usuarios(username,password_hash) VALUES (?,?)";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPasswordHash());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerPorId(int id) {

        String sql =
                "SELECT * FROM usuarios WHERE id_usuario=?";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password_hash")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Usuario> listar() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                usuarios.add(
                        new Usuario(
                                rs.getInt("id_usuario"),
                                rs.getString("username"),
                                rs.getString("password_hash")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public void actualizar(Usuario usuario) {

        String sql =
                "UPDATE usuarios SET username=?, password_hash=? WHERE id_usuario=?";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPasswordHash());
            stmt.setInt(3, usuario.getIdUsuario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {

        String sql =
                "DELETE FROM usuarios WHERE id_usuario=?";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscarPorUsername(String username) {

        String sql =
                "SELECT * FROM usuarios WHERE username=?";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password_hash")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
