package group.telina.agricole.repository;

import group.telina.agricole.entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AccountRepository {

    private final Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public Account findById(String id) {

        String sql = "SELECT * FROM account WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);  // ← String pas Integer
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getString("id"));
                a.setType(rs.getString("type"));
                a.setBalance(rs.getDouble("balance"));
                a.setHolderName(rs.getString("holder_name"));
                a.setPhoneNumber(rs.getString("phone_number"));
                a.setCollectivityId(rs.getString("collectivity_id"));
                return a;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBalance(String id, double newBalance) {

        String sql = "UPDATE account SET balance = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, id);  // ← String pas Integer
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}