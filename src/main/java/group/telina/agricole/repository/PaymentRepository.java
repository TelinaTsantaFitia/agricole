package group.telina.agricole.repository;

import group.telina.agricole.entity.Payment;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PaymentRepository {

    private final Connection connection;

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

    // =========================
    // SAVE PAYMENT
    // =========================
    public Payment save(Payment p) {

        String sql = """
            INSERT INTO payment
            (member_id, collectivity_id, amount, payment_method, account_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getMemberId());
            ps.setInt(2, p.getCollectivityId());
            ps.setDouble(3, p.getAmount());
            ps.setString(4, p.getPaymentMethod());
            ps.setInt(5, p.getAccountId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                p.setId(rs.getInt(1));
            }

            return p;

        } catch (Exception e) {
            throw new RuntimeException("Error saving payment: " + e.getMessage(), e);
        }
    }
}