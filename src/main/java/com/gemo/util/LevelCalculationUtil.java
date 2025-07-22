package com.gemo.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class LevelCalculationUtil {
    
    @Data
    public static class LevelInfo {
        private int level;
        private int currentXp;
        
        public LevelInfo(int level, int currentXp) {
            this.level = level;
            this.currentXp = currentXp;
        }
    }
    
    /**
     * 레벨별 필요한 경험치를 계산하는 함수 (단계별 시스템)
     */
    public static int getRequiredXpForLevel(int level) {
        // 1~9레벨: 초보자 구간
        if (level >= 1 && level <= 9) {
            int[] xpList = {100, 150, 200, 250, 300, 350, 400, 450, 800};
            return xpList[level - 1];
        }
        
        // 10~19레벨: 중급자 구간
        if (level >= 10 && level <= 19) {
            int[] xpList = {900, 950, 1000, 1050, 1100, 1150, 1200, 1250, 1300, 1500};
            return xpList[level - 10];
        }
        
        // 20레벨 이상: 패턴 기반 계산
        int tier = level / 10; // 2, 3, 4, ... (20~29: 2, 30~39: 3)
        int posInTier = level % 10; // 0~9 (20: 0, 21: 1, ..., 29: 9)
        
        if (tier >= 2) {
            // 각 구간별 기본값과 증가량 설정
            int baseXp = 1600 + (tier - 2) * 300; // 20레벨: 1600, 30레벨: 1900, 40레벨: 2200...
            int increment = 60 + (tier - 2) * 10; // 20~29: 60씩, 30~39: 70씩, 40~49: 80씩...
            
            if (posInTier == 9) { // 29, 39, 49... 레벨 (점프)
                return baseXp + 8 * increment + (int) Math.floor(baseXp * 0.2); // 20% 점프
            } else {
                return baseXp + posInTier * increment;
            }
        }
        
        // 500레벨 넘어가면 최대값 고정
        return 50000;
    }
    
    /**
     * 총 경험치로부터 레벨과 현재 레벨 경험치 계산
     */
    public static LevelInfo calculateLevelFromTotalXp(int totalXp) {
        int level = 1;
        int accumulatedXp = 0;
        
        while (accumulatedXp + getRequiredXpForLevel(level) <= totalXp) {
            accumulatedXp += getRequiredXpForLevel(level);
            level++;
        }
        
        int currentXp = totalXp - accumulatedXp;
        return new LevelInfo(level, currentXp);
    }
} 