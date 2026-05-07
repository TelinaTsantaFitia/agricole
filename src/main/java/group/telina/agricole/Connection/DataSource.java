package group.telina.agricole.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSource {

    private static final String URL = "jdbc:postgresql://localhost:5432/agricole";
    private static final String USER = "postgres";
    private static final String PASSWORD = "telina123";

    @Bean
    public Connection connection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erreur de connexion DB : " + e.getMessage());
        }
    }
}