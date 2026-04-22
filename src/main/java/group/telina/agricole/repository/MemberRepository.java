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

    // 🔥 LA MÉTHODE SAVE QUI MANQUAIT
    public Member save(Member member) {
        String sql = """
                INSERT INTO member (first_name, last_name, email, occupation, address, phone_number, collectivity_id, birth_date)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setString(3, member.getEmail());
            statement.setString(4, member.getOccupation());
            statement.setString(5, member.getAddress());
            statement.setObject(6, member.getPhoneNumber(), Types.INTEGER);
            statement.setObject(7, member.getCollectivityId(), Types.INTEGER);
            statement.setDate(8, member.getBirthDate() != null ? Date.valueOf(member.getBirthDate()) : null);

            statement.executeUpdate();

            // Récupération de l'ID auto-généré par PostgreSQL (SERIAL)
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                member.setId(generatedKeys.getInt(1));
            }

            // Sauvegarde des relations parrains dans la table de jointure
            if (member.getReferees() != null && member.getId() != null) {
                for (Member referee : member.getReferees()) {
                    saveRefereeRelation(member.getId(), referee.getId());
                }
            }

            return findById(member.getId());

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du membre : " + e.getMessage());
        }
    }

    public Member findById(Integer id) {
        if (id == null) return null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM member WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setFirstName(rs.getString("first_name"));
                m.setLastName(rs.getString("last_name"));
                m.setCollectivityId(rs.getInt("collectivity_id"));
                return m;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void saveRefereeRelation(Integer memberId, Integer refereeId) throws SQLException {
        String sql = "INSERT INTO member_referee (member_id, referee_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, memberId);
            statement.setInt(2, refereeId);
            statement.executeUpdate();
        }
    }
}