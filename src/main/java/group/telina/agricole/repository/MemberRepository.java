package group.telina.agricole.repository;

import group.telina.agricole.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Member> findByCollectivityId(String collectivityId) {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE collectivity_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setFirstName(rs.getString("first_name"));
                m.setLastName(rs.getString("last_name"));
                m.setEmail(rs.getString("email"));
                m.setCollectivityId(rs.getString("collectivity_id"));
                // admission_date n'existe pas encore en BDD, gérer le null
                Date admDate = rs.getDate("admission_date");
                if (admDate != null) m.setAdmissionDate(admDate.toLocalDate());
                list.add(m);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}