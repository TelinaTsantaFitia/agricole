package group.telina.agricole.repository;


import group.telina.agricole.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
    private final Connection connection;

    public MemberRepository(Connection connection) {
        this.connection = connection;
    }


    public Member save(Member member) {
        String sql = """
                INSERT INTO member (first_name, last_name, email, occupation, address, phone_number)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setString(3, member.getEmail());
            statement.setString(4, member.getOccupation());
            statement.setString(5, member.getAddress());
            statement.setInt(6, member.getPhoneNumber());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            String generatedId = null;
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getString(1);
            }

            if (member.getReferees() != null && generatedId != null) {
                for (Member referee : member.getReferees()) {
                    saveRefereeRelation(generatedId, referee.getId());
                }
            }


            return findById(generatedId);

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion SQL : " + e.getMessage());
        }
    }


    public Member findById(String id) {
        if (id == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM member WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Member m = new Member();
                m.setId(resultSet.getString("id"));
                m.setFirstName(resultSet.getString("first_name"));
                m.setLastName(resultSet.getString("last_name"));
                m.setEmail(resultSet.getString("email"));
                m.setOccupation(resultSet.getString("occupation"));
                m.setAddress(resultSet.getString("address"));
                m.setPhoneNumber(resultSet.getInt("phone_number"));

                m.setReferees(findRefereesByMemberId(id));
                return m;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Member> findRefereesByMemberId(String memberId) {
        List<Member> referees = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    """
                    SELECT m.id, m.first_name, m.last_name, m.occupation
                    FROM member m
                    JOIN member_referee mr ON m.id = mr.id_referee
                    WHERE mr.id_member = ?
                    """);
            statement.setString(1, memberId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Member ref = new Member();
                ref.setId(rs.getString("id"));
                ref.setFirstName(rs.getString("first_name"));
                ref.setLastName(rs.getString("last_name"));
                ref.setOccupation(rs.getString("occupation"));
                referees.add(ref);
            }
            return referees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void saveRefereeRelation(String memberId, String refereeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO member_referee (id_member, id_referee) VALUES (?, ?)");
        statement.setString(1, memberId);
        statement.setString(2, refereeId);
        statement.executeUpdate();
    }

    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM member");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString("id"));
                // ... remplissage des champs ...
                m.setReferees(findRefereesByMemberId(m.getId()));
                members.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }


}