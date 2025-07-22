package com.gemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor 
@AllArgsConstructor
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
} 