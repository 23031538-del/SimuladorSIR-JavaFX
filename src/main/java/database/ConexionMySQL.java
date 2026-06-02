package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    private static ConexionMySQL instancia;

    private Connection conexion;

    private static final String URL =
            "jdbc:mysql://localhost:3306/sir_simulator";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "4l1c31n4d4rkw0nd3rl@nd_n16h7m4r3!b3f0r3chr1stm@s?";

    private ConexionMySQL() throws SQLException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "Driver MySQL no encontrado."
            );
        }
    }

    public static ConexionMySQL getInstance()
            throws SQLException {

        if (instancia == null
                || instancia.conexion.isClosed()) {

            instancia =
                    new ConexionMySQL();
        }

        return instancia;
    }

    public Connection getConnection() {

        return conexion;
    }
}
