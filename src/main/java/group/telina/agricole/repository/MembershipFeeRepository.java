package group.telina.agricole.repository;

import group.telina.agricole.entity.MembershipFee;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {

    private final Connection connection;

    public MembershipFeeRepository(Connection connection) {
        this.connection = connection;
    }

    public List<MembershipFee> findByCollectivityIdAndStatus(String collectivityId, String status) {
        List<MembershipFee> list = new ArrayList<>();
        String sql = "SELECT * FROM membership_fee WHERE collectivity_id = ? AND status = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MembershipFee fee = new MembershipFee();
                fee.setId(rs.getString("id"));
                fee.setLabel(rs.getString("label"));
                fee.setStatus(rs.getString("status"));
                fee.setFrequency(rs.getString("frequency"));
                fee.setAmount(rs.getBigDecimal("amount"));
                fee.setEligibleSince(rs.getDate("eligible_since").toLocalDate());
                fee.setCollectivityId(rs.getString("collectivity_id"));
                list.add(fee);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}