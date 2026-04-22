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
            (first_name, last_name, email, collectivity_id)
            VALUES (?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getFirstName());
            ps.setString(2, m.getLastName());
            ps.setString(3, m.getEmail());
            ps.setInt(4, m.getCollectivityId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                m.setId(rs.getInt(1));
            }

            return m;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}