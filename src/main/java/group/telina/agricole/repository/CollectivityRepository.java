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

    // POST
    public Collectivity save(Collectivity c) {

        String sql = """
            INSERT INTO collectivity
            (collectivity_number, name, address, collectivity_type, email, phone_number)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getNumber());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getCollectivityType());
            ps.setString(5, c.getEmail());
            ps.setInt(6, c.getPhoneNumber());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                c.setId(rs.getInt(1));
            }

            return c;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // GET ALL
    public List<Collectivity> findAll() {

        List<Collectivity> list = new ArrayList<>();

        String sql = "SELECT * FROM collectivity";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Collectivity c = new Collectivity();
                c.setId(rs.getInt("id"));
                c.setNumber(rs.getInt("collectivity_number"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setCollectivityType(rs.getString("collectivity_type"));
                c.setEmail(rs.getString("email"));
                c.setPhoneNumber(rs.getInt("phone_number"));

                list.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}