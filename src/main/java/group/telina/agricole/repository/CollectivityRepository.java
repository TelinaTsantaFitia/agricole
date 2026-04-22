package group.telina.agricole.repository;

import group.telina.agricole.entity.Collectivity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityRepository {
    private final Connection connection;

    public CollectivityRepository(Connection connection) {
        this.connection = connection;
    }

    public Collectivity save(Collectivity collectivity) {
        String sql = """
                INSERT INTO collectivity (name, address, collectivity_type, email, phone_number)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, collectivity.getName());
            statement.setString(2, collectivity.getAddress());
            statement.setString(3, collectivity.getCollectivityType());
            statement.setString(4, collectivity.getEmail());
            statement.setInt(5, collectivity.getPhoneNumber());

            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            String generatedId = null;
            if (keys.next()) {
                generatedId = keys.getString(1);
            }

            return findById(generatedId);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de la collectivité : " + e.getMessage());
        }
    }

    public Collectivity findById(String id) {
        if (id == null) return null;

        String sql = "SELECT * FROM collectivity WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Collectivity(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("collectivity_type"),
                        rs.getString("email"),
                        rs.getInt("phone_number")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération : " + e.getMessage());
        }
    }

    public List<Collectivity> findAll() {
        List<Collectivity> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM collectivity");
            while (rs.next()) {
                list.add(new Collectivity(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("collectivity_type"),
                        rs.getString("email"),
                        rs.getInt("phone_number")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
