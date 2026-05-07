package group.telina.agricole.repository;

import group.telina.agricole.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MemberRepository {

    private final Connection connection;

    public MemberRepository(Connection connection) {
        this.connection = connection;
    }

    public Member save(Member m) {

        String sql = """
            INSERT INTO member
            (id, first_name, last_name, email, collectivity_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, m.getId());           // ← id fourni par l'appelant
            ps.setString(2, m.getFirstName());
            ps.setString(3, m.getLastName());
            ps.setString(4, m.getEmail());
            ps.setString(5, m.getCollectivityId()); // ← String, pas setInt

            ps.executeUpdate();
            return m;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}