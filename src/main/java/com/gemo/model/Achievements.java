package com.gemo.model;

import java.util.HashMap;
import java.util.Map;

public class Achievements {
    private Map<String, AttendanceAchievement> attendance = new HashMap<>();
    
    // 기본 생성자 - 기본 업적들로 초기화
    public Achievements() {
        initializeDefaultAchievements();
    }
    
    // 생성자
    public Achievements(Map<String, AttendanceAchievement> attendance) {
        this.attendance = attendance != null ? attendance : new HashMap<>();
        if (this.attendance.isEmpty()) {
            initializeDefaultAchievements();
        }
    }
    
    /**
     * 기본 출석 업적들로 초기화
     */
    private void initializeDefaultAchievements() {
        attendance.put("d1", new AttendanceAchievement(false, "첫 출석 완료! 연속 1일 달성"));
        attendance.put("d7", new AttendanceAchievement(false, "연속 7일 출석 달성"));
        attendance.put("d14", new AttendanceAchievement(false, "연속 14일 출석 달성"));
        attendance.put("d21", new AttendanceAchievement(false, "연속 21일 출석 달성"));
        attendance.put("d28", new AttendanceAchievement(false, "연속 28일 출석 달성"));
    }
    
    // Getter 메서드들
    public Map<String, AttendanceAchievement> getAttendance() { return attendance; }
    
    // Setter 메서드들
    public void setAttendance(Map<String, AttendanceAchievement> attendance) { 
        this.attendance = attendance != null ? attendance : new HashMap<>();
    }
    
    /**
     * 특정 업적을 완료로 설정
     */
    public void completeAchievement(String key) {
        if (attendance.containsKey(key)) {
            attendance.get(key).setCompleted(true);
        }
    }
    
    /**
     * 특정 업적의 완료 상태 확인
     */
    public boolean isAchievementCompleted(String key) {
        return attendance.containsKey(key) && attendance.get(key).isCompleted();
    }
}
