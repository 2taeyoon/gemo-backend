package com.gemo.model;

import java.time.LocalDateTime;

public class GameData {
    // 코들 게임 관련 통계
    private int kodleGameWins = 0;                    // 코들 게임 총 승리 횟수
    private int kodleGameDefeat = 0;                  // 코들 게임 총 패배 횟수
    private int kodleSuccessiveVictory = 0;           // 코들 게임 현재 연속 승리
    private int kodleMaximumSuccessiveVictory = 0;    // 코들 게임 최대 연속 승리 기록
    
    // 기존 호환성을 위한 필드들
    private int gameWins = 0;        // 하위 호환성
    private int consecutiveWins = 0; // 하위 호환성
    
    // 경험치 및 레벨 시스템
    private int totalXp = 0;         // 총 누적 경험치
    private int currentXp = 0;       // 현재 레벨에서의 경험치
    private int level = 1;           // 현재 레벨
    
    // 출석 관련
    private LocalDateTime lastAttendance;  // 마지막 출석 날짜
    private int attendanceStreak = 0;      // 연속 출석 일수
    
    // 기타 게임 통계
    private LocalDateTime lastGamePlayed;  // 마지막 게임 플레이 시간
    private int totalGamesPlayed = 0;      // 총 게임 플레이 횟수
    
    // 기본 생성자
    public GameData() {
    }
    
    // 전체 생성자
    public GameData(int kodleGameWins, int kodleGameDefeat, int kodleSuccessiveVictory, 
                   int kodleMaximumSuccessiveVictory, int gameWins, int consecutiveWins, 
                   int totalXp, int currentXp, int level, LocalDateTime lastAttendance, 
                   int attendanceStreak, LocalDateTime lastGamePlayed, int totalGamesPlayed) {
        this.kodleGameWins = kodleGameWins;
        this.kodleGameDefeat = kodleGameDefeat;
        this.kodleSuccessiveVictory = kodleSuccessiveVictory;
        this.kodleMaximumSuccessiveVictory = kodleMaximumSuccessiveVictory;
        this.gameWins = gameWins;
        this.consecutiveWins = consecutiveWins;
        this.totalXp = totalXp;
        this.currentXp = currentXp;
        this.level = level;
        this.lastAttendance = lastAttendance;
        this.attendanceStreak = attendanceStreak;
        this.lastGamePlayed = lastGamePlayed;
        this.totalGamesPlayed = totalGamesPlayed;
    }
    
    // Getter 메서드들
    public int getKodleGameWins() { return kodleGameWins; }
    public int getKodleGameDefeat() { return kodleGameDefeat; }
    public int getKodleSuccessiveVictory() { return kodleSuccessiveVictory; }
    public int getKodleMaximumSuccessiveVictory() { return kodleMaximumSuccessiveVictory; }
    public int getGameWins() { return gameWins; }
    public int getConsecutiveWins() { return consecutiveWins; }
    public int getTotalXp() { return totalXp; }
    public int getCurrentXp() { return currentXp; }
    public int getLevel() { return level; }
    public LocalDateTime getLastAttendance() { return lastAttendance; }
    public int getAttendanceStreak() { return attendanceStreak; }
    public LocalDateTime getLastGamePlayed() { return lastGamePlayed; }
    public int getTotalGamesPlayed() { return totalGamesPlayed; }
    
    // Setter 메서드들
    public void setKodleGameWins(int kodleGameWins) { this.kodleGameWins = kodleGameWins; }
    public void setKodleGameDefeat(int kodleGameDefeat) { this.kodleGameDefeat = kodleGameDefeat; }
    public void setKodleSuccessiveVictory(int kodleSuccessiveVictory) { this.kodleSuccessiveVictory = kodleSuccessiveVictory; }
    public void setKodleMaximumSuccessiveVictory(int kodleMaximumSuccessiveVictory) { this.kodleMaximumSuccessiveVictory = kodleMaximumSuccessiveVictory; }
    public void setGameWins(int gameWins) { this.gameWins = gameWins; }
    public void setConsecutiveWins(int consecutiveWins) { this.consecutiveWins = consecutiveWins; }
    public void setTotalXp(int totalXp) { this.totalXp = totalXp; }
    public void setCurrentXp(int currentXp) { this.currentXp = currentXp; }
    public void setLevel(int level) { this.level = level; }
    public void setLastAttendance(LocalDateTime lastAttendance) { this.lastAttendance = lastAttendance; }
    public void setAttendanceStreak(int attendanceStreak) { this.attendanceStreak = attendanceStreak; }
    public void setLastGamePlayed(LocalDateTime lastGamePlayed) { this.lastGamePlayed = lastGamePlayed; }
    public void setTotalGamesPlayed(int totalGamesPlayed) { this.totalGamesPlayed = totalGamesPlayed; }
} 