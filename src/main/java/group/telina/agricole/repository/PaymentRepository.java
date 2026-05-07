package group.telina.agricole.repository;

import group.telina.agricole.entity.Payment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private final Connection connection;

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

    public Payment save(Payment p) {

        String sql = """
            INSERT INTO payment
            (member_id, collectivity_id, amount, payment_method, account_id, payment_date)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getMemberId());        // ← String
            ps.setString(2, p.getCollectivityId());  // ← String
            ps.setDouble(3, p.getAmount());
            ps.setString(4, p.getPaymentMethod());
            ps.setString(5, p.getAccountId());       // ← String
            ps.setDate(6, Date.valueOf(p.getPaymentDate()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1)); // id SERIAL → Integer OK
            }

            return p;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Payment> findByCollectivityId(String collectivityId) {

        List<Payment> list = new ArrayList<>();

        String sql = "SELECT * FROM payment WHERE collectivity_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment p = new Payment();
                p.setId(rs.getInt("id"));
                p.setMemberId(rs.getString("member_id"));
                p.setCollectivityId(rs.getString("collectivity_id"));
                p.setAccountId(rs.getString("account_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPaymentMethod(rs.getString("payment_method"));
                p.setPaymentDate(rs.getDate("payment_date").toLocalDate());
                list.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    public List<Payment> findByCollectivityIdAndPaymentDateBetween(
            String collectivityId, LocalDate from, LocalDate to) {

        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payment WHERE collectivity_id = ? AND payment_date BETWEEN ? AND ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payment p = new Payment();
                p.setId(rs.getInt("id"));
                p.setMemberId(rs.getString("member_id"));
                p.setCollectivityId(rs.getString("collectivity_id"));
                p.setAccountId(rs.getString("account_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPaymentMethod(rs.getString("payment_method"));
                p.setPaymentDate(rs.getDate("payment_date").toLocalDate());
                list.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}