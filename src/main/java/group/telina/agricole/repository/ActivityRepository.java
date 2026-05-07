package group.telina.agricole.repository;

import group.telina.agricole.entity.Activity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityRepository {

    private final Connection connection;

    public ActivityRepository(Connection connection) {
        this.connection = connection;
    }

    public Activity save(Activity a) {
        String sql = """
            INSERT INTO activity (id, label, type, activity_date, mandatory, collectivity_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, a.getId());
            ps.setString(2, a.getLabel());
            ps.setString(3, a.getType());
            ps.setDate(4, Date.valueOf(a.getActivityDate()));
            ps.setBoolean(5, a.isMandatory());
            ps.setString(6, a.getCollectivityId());
            ps.executeUpdate();
            return a;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Activity> findByCollectivityId(String collectivityId) {
        List<Activity> list = new ArrayList<>();
        String sql = "SELECT * FROM activity WHERE collectivity_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Activity a = new Activity();
                a.setId(rs.getString("id"));
                a.setLabel(rs.getString("label"));
                a.setType(rs.getString("type"));
                a.setActivityDate(rs.getDate("activity_date").toLocalDate());
                a.setMandatory(rs.getBoolean("mandatory"));
                a.setCollectivityId(rs.getString("collectivity_id"));
                list.add(a);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Activity findById(String id) {
        String sql = "SELECT * FROM activity WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Activity a = new Activity();
                a.setId(rs.getString("id"));
                a.setLabel(rs.getString("label"));
                a.setType(rs.getString("type"));
                a.setActivityDate(rs.getDate("activity_date").toLocalDate());
                a.setMandatory(rs.getBoolean("mandatory"));
                a.setCollectivityId(rs.getString("collectivity_id"));
                return a;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Activity> findByCollectivityIdAndDateBetween(
            String collectivityId, LocalDate from, LocalDate to) {
        List<Activity> list = new ArrayList<>();
        String sql = "SELECT * FROM activity WHERE collectivity_id = ? AND activity_date BETWEEN ? AND ? AND mandatory = true";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            ps.setDate(2, Date.valueOf(String.valueOf(from)));
            ps.setDate(3, Date.valueOf(String.valueOf(to)));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Activity a = new Activity();
                a.setId(rs.getString("id"));
                a.setLabel(rs.getString("label"));
                a.setType(rs.getString("type"));
                a.setActivityDate(rs.getDate("activity_date").toLocalDate());
                a.setMandatory(rs.getBoolean("mandatory"));
                a.setCollectivityId(rs.getString("collectivity_id"));
                list.add(a);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}