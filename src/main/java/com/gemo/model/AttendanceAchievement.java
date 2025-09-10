package com.gemo.model;

public class AttendanceAchievement {
    private boolean completed;
    private String text;
    
    // 기본 생성자
    public AttendanceAchievement() {
    }
    
    // 생성자
    public AttendanceAchievement(boolean completed, String text) {
        this.completed = completed;
        this.text = text;
    }
    
    // Getter 메서드들
    public boolean isCompleted() { return completed; }
    public String getText() { return text; }
    
    // Setter 메서드들
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setText(String text) { this.text = text; }
}
