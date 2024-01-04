import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexiuneBazadeDate {
    private static final String URL = "jdbc:mysql://localhost:3306/proiectprogramare3";
    private static final String UTILIZATOR = "root";
    private static final String PAROLA = "";

    public static Connection obtineConexiune() {
        try {
            return DriverManager.getConnection(URL, UTILIZATOR, PAROLA);
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la conectarea la baza de date.", e);
        }
    }
}
