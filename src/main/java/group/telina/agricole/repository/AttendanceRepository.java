package group.telina.agricole.repository;

import group.telina.agricole.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AttendanceRepository {

    private final Connection connection;

    public AttendanceRepository(Connection connection) {
        this.connection = connection;
    }

    public Attendance save(Attendance a) {
        String sql = """
            INSERT INTO attendance (activity_id, member_id, present)
            VALUES (?, ?, ?)
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getActivityId());
            ps.setString(2, a.getMemberId());
            ps.setBoolean(3, a.isPresent());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) a.setId(rs.getInt(1));
            return a;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByActivityIdAndMemberId(String activityId, String memberId) {
        String sql = "SELECT COUNT(*) FROM attendance WHERE activity_id = ? AND member_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, activityId);
            ps.setString(2, memberId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Attendance> findByActivityId(String activityId) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE activity_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, activityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setId(rs.getInt("id"));
                a.setActivityId(rs.getString("activity_id"));
                a.setMemberId(rs.getString("member_id"));
                a.setPresent(rs.getBoolean("present"));
                list.add(a);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}