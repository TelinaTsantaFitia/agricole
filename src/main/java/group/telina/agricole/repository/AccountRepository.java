package group.telina.agricole.repository;

import java.sql.Connection;
import group.telina.agricole.entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class AccountRepository {

    private final Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    // =========================
    // CREATE ACCOUNT
    // =========================
    public Account save(Account a) {

        String sql = """
            INSERT INTO account
            (collectivity_id, type, provider, account_number, balance)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, a.getCollectivityId());
            ps.setString(2, a.getType());
            ps.setString(3, a.getProvider());
            ps.setString(4, a.getAccountNumber());
            ps.setDouble(5, a.getBalance());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }

            return a;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // =========================
    // UPDATE BALANCE
    // =========================
    public void updateBalance(Integer id, Double newBalance) {

        String sql = "UPDATE account SET balance = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findById(Integer id) {

        String sql = "SELECT * FROM account WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setCollectivityId(rs.getInt("collectivity_id"));
                a.setType(rs.getString("type"));
                a.setProvider(rs.getString("provider"));
                a.setAccountNumber(rs.getString("account_number"));
                a.setBalance(rs.getDouble("balance"));
                return a;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}