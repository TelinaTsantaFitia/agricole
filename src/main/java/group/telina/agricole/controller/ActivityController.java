package group.telina.agricole.controller;

import group.telina.agricole.entity.Activity;
import group.telina.agricole.entity.Attendance;
import group.telina.agricole.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    // POST /collectivities/{id}/activities
    @PostMapping("/{id}/activities")
    public ResponseEntity<List<Activity>> create(
            @PathVariable String id,
            @RequestBody List<Activity> activities) {
        return ResponseEntity.status(201).body(activityService.create(id, activities));
    }

    // GET /collectivities/{id}/activities
    @GetMapping("/{id}/activities")
    public ResponseEntity<List<Activity>> getAll(@PathVariable String id) {
        return ResponseEntity.ok(activityService.getByCollectivityId(id));
    }

    // POST /collectivities/{id}/activities/{activityId}/attendance
    @PostMapping("/{id}/activities/{activityId}/attendance")
    public ResponseEntity<List<Attendance>> saveAttendance(
            @PathVariable String id,
            @PathVariable String activityId,
            @RequestBody List<Attendance> attendances) {
        return ResponseEntity.status(201).body(activityService.saveAttendance(activityId, attendances));
    }

    // GET /collectivities/{id}/activities/{activityId}/attendance
    @GetMapping("/{id}/activities/{activityId}/attendance")
    public ResponseEntity<List<Attendance>> getAttendance(
            @PathVariable String id,
            @PathVariable String activityId) {
        return ResponseEntity.ok(activityService.getAttendance(activityId));
    }
}