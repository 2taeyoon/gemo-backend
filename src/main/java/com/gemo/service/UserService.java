package com.gemo.service;

import com.gemo.model.User;
import com.gemo.model.GameData;
import com.gemo.repository.UserRepository;
import com.gemo.util.LevelCalculationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 사용자 조회 (이메일로)
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * 사용자 조회 (ID로)
     */
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
    
    /**
     * 사용자 생성 또는 업데이트 (Google OAuth 로그인용)
     */
    public User createOrUpdateUser(String email, String name, String picture, String googleId) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setPicture(picture);
            user.setGoogleId(googleId);
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPicture(picture);
            newUser.setGoogleId(googleId);
            newUser.setGameData(new GameData());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
        }
    }
    
    /**
     * 사용자 생성 또는 업데이트 (Naver OAuth 로그인용)
     */
    public User createOrUpdateNaverUser(String email, String name, String picture, String naverId) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(name);
            user.setPicture(picture);
            user.setNaverId(naverId);
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPicture(picture);
            newUser.setNaverId(naverId);
            newUser.setGameData(new GameData());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
        }
    }
    
    /**
     * 코들 게임 승리 처리 (연승에 따른 경험치 시스템)
     */
    public User processKodleGameWin(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        
        GameData gameData = user.getGameData();
        
        // 연승 증가 (경험치 계산을 위해 먼저)
        int newStreak = gameData.getKodleSuccessiveVictory() + 1;
        
        // 연승에 따른 경험치 계산
        int victoryXp = calculateKodleWinXp(newStreak);
        
        // 게임 통계 업데이트
        gameData.setKodleGameWins(gameData.getKodleGameWins() + 1);
        gameData.setKodleSuccessiveVictory(newStreak);
        gameData.setKodleMaximumSuccessiveVictory(
            Math.max(newStreak, gameData.getKodleMaximumSuccessiveVictory())
        );
        
        // 하위 호환성 필드 업데이트
        gameData.setGameWins(gameData.getKodleGameWins());
        gameData.setConsecutiveWins(gameData.getKodleSuccessiveVictory());
        
        // 경험치 지급 (연승에 따른 계산)
        int newTotalXp = gameData.getTotalXp() + victoryXp;
        gameData.setTotalXp(newTotalXp);
        
        // 레벨 및 현재 경험치 재계산
        LevelCalculationUtil.LevelInfo levelInfo = LevelCalculationUtil.calculateLevelFromTotalXp(newTotalXp);
        gameData.setLevel(levelInfo.getLevel());
        gameData.setCurrentXp(levelInfo.getCurrentXp());
        
        // 게임 플레이 기록 업데이트
        gameData.setLastGamePlayed(LocalDateTime.now());
        gameData.setTotalGamesPlayed(gameData.getTotalGamesPlayed() + 1);
        
        user.setUpdatedAt(LocalDateTime.now());
        
        log.info("🏆 코들 게임 승리 처리: {} - {}연승, {}XP 획득, 총 승리: {}, 레벨: {}", 
                user.getEmail(), newStreak, victoryXp, gameData.getKodleGameWins(), gameData.getLevel());
        
        return userRepository.save(user);
    }
    
    /**
     * 코들 게임 패배 처리 (패배 시에도 소량의 경험치 지급)
     */
    public User processKodleGameDefeat(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        
        GameData gameData = user.getGameData();
        
        // 게임 통계 업데이트
        gameData.setKodleGameDefeat(gameData.getKodleGameDefeat() + 1);
        gameData.setKodleSuccessiveVictory(0); // 연속 승리 초기화
        
        // 하위 호환성 필드 업데이트
        gameData.setConsecutiveWins(0);
        
        // 패배 시에도 소량의 경험치 지급 (20XP)
        int defeatXp = 20;
        int newTotalXp = gameData.getTotalXp() + defeatXp;
        gameData.setTotalXp(newTotalXp);
        
        // 레벨 및 현재 경험치 재계산
        LevelCalculationUtil.LevelInfo levelInfo = LevelCalculationUtil.calculateLevelFromTotalXp(newTotalXp);
        gameData.setLevel(levelInfo.getLevel());
        gameData.setCurrentXp(levelInfo.getCurrentXp());
        
        // 게임 플레이 기록 업데이트
        gameData.setLastGamePlayed(LocalDateTime.now());
        gameData.setTotalGamesPlayed(gameData.getTotalGamesPlayed() + 1);
        
        user.setUpdatedAt(LocalDateTime.now());
        
        log.info("💔 코들 게임 패배 처리: {} - {}XP 획득, 총 패배: {}, 연속 승리 초기화", 
                user.getEmail(), defeatXp, gameData.getKodleGameDefeat());
        
        return userRepository.save(user);
    }
    
    /**
     * 일반 게임 승리 처리 (하위 호환성)
     */
    public User processGameWin(String userId) {
        return processKodleGameWin(userId);
    }
    
    /**
     * 출석 체크
     */
    public User processAttendance(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        
        GameData gameData = user.getGameData();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastAttendance = gameData.getLastAttendance();
        
        // 오늘 이미 출석했는지 확인
        if (lastAttendance != null && lastAttendance.toLocalDate().equals(now.toLocalDate())) {
            throw new RuntimeException("오늘 이미 출석했습니다.");
        }
        
        // 연속 출석 계산
        if (lastAttendance != null && lastAttendance.toLocalDate().equals(now.toLocalDate().minusDays(1))) {
            gameData.setAttendanceStreak(gameData.getAttendanceStreak() + 1);
        } else {
            gameData.setAttendanceStreak(1); // 연속 출석 초기화
        }
        
        gameData.setLastAttendance(now);
        user.setUpdatedAt(LocalDateTime.now());
        
        log.info("📅 출석 체크: {} - 연속 출석: {}일", user.getEmail(), gameData.getAttendanceStreak());
        
        return userRepository.save(user);
    }
    
    /**
     * 사용자 프로필 조회
     */
    public User getUserProfile(String userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
    
    /**
     * 연승에 따른 코들 게임 승리 경험치 계산
     * 
     * 계산 로직:
     * - 1승: 기본 100xp
     * - 2-9연승: 이전 경험치 + (이전 경험치 * 20%) - 반올림
     * - 10연승: 825xp (특별 보너스)
     * - 11연승 이상: 825xp (고정)
     * 
     * @param currentWinStreak 현재 연속 승리 횟수 (이번 승리 포함)
     * @return 획득할 경험치
     */
    private int calculateKodleWinXp(int currentWinStreak) {
        if (currentWinStreak == 1) {
            return 100; // 기본 경험치
        }
        
        // 10연승 이상은 고정 825xp
        if (currentWinStreak >= 10) {
            return 825;
        }
        
        // 2-9연승: 이전 연승의 경험치에 20% 보너스 적용하여 계산
        int totalXp = 100; // 1승 기본 경험치
        
        for (int streak = 2; streak <= currentWinStreak; streak++) {
            double bonus = totalXp * 0.2; // 20% 보너스
            totalXp = totalXp + (int) Math.round(bonus); // 반올림하여 적용
        }
        
        return totalXp;
    }
} 