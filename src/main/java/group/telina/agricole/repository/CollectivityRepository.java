package group.telina.agricole.repository;

import group.telina.agricole.entity.Collectivity;
import group.telina.agricole.entity.FinancialAccount;
import group.telina.agricole.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityRepository {

    private final Connection connection;

    public CollectivityRepository(Connection connection) {
        this.connection = connection;
    }

    // ─────────────────────────────────────────
    // POST /collectivities
    // ─────────────────────────────────────────
    public Collectivity save(Collectivity c) {

        String sql = """
            INSERT INTO collectivity (id, number, name, locality, specialization)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, c.getId());
            ps.setInt(2, c.getNumber());
            ps.setString(3, c.getName());
            ps.setString(4, c.getAddress());        // address = locality
            ps.setString(5, c.getCollectivityType()); // collectivityType = specialization

            ps.executeUpdate();
            return c;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ─────────────────────────────────────────
    // GET /collectivities (liste complète)
    // ─────────────────────────────────────────
    public List<Collectivity> findAll() {

        List<Collectivity> list = new ArrayList<>();
        String sql = "SELECT * FROM collectivity";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapCollectivity(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // ─────────────────────────────────────────
    // GET /collectivities/{id}
    // ─────────────────────────────────────────
    public Collectivity findById(String id) {

        String sql = "SELECT * FROM collectivity WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapCollectivity(rs);
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ─────────────────────────────────────────
    // GET membres d'une collectivité
    // ─────────────────────────────────────────
    public List<Member> findMembersByCollectivityId(String collectivityId) {

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
                list.add(m);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // ─────────────────────────────────────────
    // GET /collectivities/{id}/financialAccounts
    // ─────────────────────────────────────────
    public List<FinancialAccount> findFinancialAccounts(String collectivityId, LocalDate at) {

        List<FinancialAccount> list = new ArrayList<>();

        // Solde = balance initiale du compte + paiements reçus jusqu'à la date "at"
        String sql = """
            SELECT
                a.id,
                a.type,
                a.holder_name,
                a.phone_number,
                a.balance + COALESCE(
                    (SELECT SUM(p.amount)
                     FROM payment p
                     WHERE p.account_id = a.id
                     AND p.payment_date <= ?),
                0) AS balance_at_date
            FROM account a
            WHERE a.collectivity_id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(at));
            ps.setString(2, collectivityId); // ← String, pas Integer !

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FinancialAccount fa = new FinancialAccount(
                        rs.getString("id"),
                        rs.getString("type"),
                        rs.getBigDecimal("balance_at_date")
                );
                list.add(fa);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // ─────────────────────────────────────────
    // Méthode utilitaire privée
    // ─────────────────────────────────────────
    private Collectivity mapCollectivity(ResultSet rs) throws SQLException {
        Collectivity c = new Collectivity();
        c.setId(rs.getString("id"));
        c.setNumber(rs.getInt("number"));
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("locality"));
        c.setCollectivityType(rs.getString("specialization"));
        return c;
    }
}