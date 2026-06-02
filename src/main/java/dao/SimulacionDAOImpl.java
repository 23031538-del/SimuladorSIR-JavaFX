package dao;

import database.ConexionMySQL;
import model.Resultado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimulacionDAOImpl implements SimulacionDAO {

    private Connection conexion;

    public SimulacionDAOImpl() throws SQLException {
        conexion = ConexionMySQL.getInstance().getConnection();
    }

    @Override
    public void insertar(Resultado resultado) {

        String sql =
                "INSERT INTO simulaciones(tiempo,susceptibles,infectados,recuperados) VALUES (?,?,?,?)";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setInt(1, resultado.getTiempo());
            stmt.setInt(2, resultado.getSusceptibles());
            stmt.setInt(3, resultado.getInfectados());
            stmt.setInt(4, resultado.getRecuperados());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resultado obtenerPorId(int id) {

        String sql =
                "SELECT * FROM simulaciones WHERE id_simulacion=?";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Resultado(
                        rs.getInt("tiempo"),
                        rs.getInt("susceptibles"),
                        rs.getInt("infectados"),
                        rs.getInt("recuperados")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Resultado> listar() {

        List<Resultado> resultados = new ArrayList<>();

        String sql = "SELECT * FROM simulaciones";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                resultados.add(
                        new Resultado(
                                rs.getInt("tiempo"),
                                rs.getInt("susceptibles"),
                                rs.getInt("infectados"),
                                rs.getInt("recuperados")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }

    @Override
    public void actualizar(Resultado resultado) {

        System.out.println(
                "Los resultados históricos no serán modificados."
        );
    }

    @Override
    public void eliminar(int id) {

        String sql =
                "DELETE FROM simulaciones WHERE id_simulacion=?";

        try (PreparedStatement stmt =
                     conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}