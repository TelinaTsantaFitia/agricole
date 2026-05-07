package group.telina.agricole.service;

import group.telina.agricole.entity.Activity;
import group.telina.agricole.entity.Attendance;
import group.telina.agricole.repository.ActivityRepository;
import group.telina.agricole.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final AttendanceRepository attendanceRepository;

    public ActivityService(ActivityRepository activityRepository,
                           AttendanceRepository attendanceRepository) {
        this.activityRepository = activityRepository;
        this.attendanceRepository = attendanceRepository;
    }

    // POST /collectivities/{id}/activities
    public List<Activity> create(String collectivityId, List<Activity> activities) {
        activities.forEach(a -> {
            a.setCollectivityId(collectivityId);
            activityRepository.save(a);
        });
        return activities;
    }

    // GET /collectivities/{id}/activities
    public List<Activity> getByCollectivityId(String collectivityId) {
        return activityRepository.findByCollectivityId(collectivityId);
    }

    // POST /collectivities/{id}/activities/{activityId}/attendance
    public List<Attendance> saveAttendance(String activityId, List<Attendance> attendances) {
        Activity activity = activityRepository.findById(activityId);
        if (activity == null) {
            throw new RuntimeException("Activity not found: " + activityId);
        }

        attendances.forEach(a -> {
            a.setActivityId(activityId);
            // Vérifier si déjà marqué — on ne peut pas modifier
            if (attendanceRepository.existsByActivityIdAndMemberId(activityId, a.getMemberId())) {
                throw new RuntimeException("Attendance already recorded for member: " + a.getMemberId());
            }
            attendanceRepository.save(a);
        });

        return attendances;
    }

    // GET /collectivities/{id}/activities/{activityId}/attendance
    public List<Attendance> getAttendance(String activityId) {
        Activity activity = activityRepository.findById(activityId);
        if (activity == null) {
            throw new RuntimeException("Activity not found: " + activityId);
        }
        return attendanceRepository.findByActivityId(activityId);
    }
}