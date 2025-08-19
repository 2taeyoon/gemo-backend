package com.gemo.controller;

import com.gemo.model.User;
import com.gemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * 사용자 프로필 조회
     * GET /api/user/profile/{userId}
     */
    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable String userId) {
        try {
            User user = userService.getUserProfile(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", createUserResponseData(user));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            log.error("사용자 프로필 조회 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 코들 게임 승리 처리
     * POST /api/user/kodle-game-win/{userId}
     */
    @PostMapping("/kodle-game-win/{userId}")
    public ResponseEntity<?> processKodleGameWin(@PathVariable String userId) {
        try {
            User user = userService.processKodleGameWin(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "코들 게임 승리가 기록되었습니다!");
            response.put("data", createGameResponseData(user));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            log.error("코들 게임 승리 처리 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 코들 게임 패배 처리
     * POST /api/user/kodle-game-defeat/{userId}
     */
    @PostMapping("/kodle-game-defeat/{userId}")
    public ResponseEntity<?> processKodleGameDefeat(@PathVariable String userId) {
        try {
            User user = userService.processKodleGameDefeat(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "코들 게임 패배가 기록되었습니다.");
            response.put("data", createGameResponseData(user));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            log.error("코들 게임 패배 처리 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 일반 게임 승리 처리 (하위 호환성)
     * POST /api/user/game-win/{userId}
     */
    @PostMapping("/game-win/{userId}")
    public ResponseEntity<?> processGameWin(@PathVariable String userId) {
        try {
            User user = userService.processGameWin(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "게임 승리가 기록되었습니다!");
            response.put("data", createGameResponseData(user));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            log.error("게임 승리 처리 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 출석 체크
     * POST /api/user/attendance/{userId}
     */
    @PostMapping("/attendance/{userId}")
    public ResponseEntity<?> processAttendance(@PathVariable String userId) {
        try {
            User user = userService.processAttendance(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "출석이 완료되었습니다!");
            response.put("data", createAttendanceResponseData(user));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            log.error("출석 처리 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    /**
     * 연속 승리 기록 초기화
     * POST /api/user/reset-win-streak/{userId}
     */
    @PostMapping("/reset-win-streak/{userId}")
    public ResponseEntity<?> resetWinStreak(@PathVariable String userId) {
        try {
            User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
            
            user.getGameData().setKodleSuccessiveVictory(0);
            user.getGameData().setConsecutiveWins(0);
            userService.getUserProfile(userId); // 저장을 위해
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "연속 승리 기록이 초기화되었습니다.");
            response.put("data", createGameResponseData(user));
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            log.error("연속 승리 초기화 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(createErrorResponse(e.getMessage()));
        }
    }
    
    // ===== Helper Methods =====
    
    private Map<String, Object> createUserResponseData(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("email", user.getEmail());
        data.put("name", user.getName());
        data.put("picture", user.getPicture());
        data.put("gameData", user.getGameData());
        data.put("createdAt", user.getCreatedAt());
        data.put("updatedAt", user.getUpdatedAt());
        return data;
    }
    
    private Map<String, Object> createGameResponseData(User user) {
        Map<String, Object> data = new HashMap<>();
        
        // 코들 게임 데이터
        data.put("kodleGameWins", user.getGameData().getKodleGameWins());
        data.put("kodleGameDefeat", user.getGameData().getKodleGameDefeat());
        data.put("kodleSuccessiveVictory", user.getGameData().getKodleSuccessiveVictory());
        data.put("kodleMaximumSuccessiveVictory", user.getGameData().getKodleMaximumSuccessiveVictory());
        
        // 하위 호환성 데이터
        data.put("gameWins", user.getGameData().getGameWins());
        data.put("consecutiveWins", user.getGameData().getConsecutiveWins());
        
        // 경험치 및 레벨 데이터
        data.put("level", user.getGameData().getLevel());
        data.put("currentXp", user.getGameData().getCurrentXp());
        data.put("totalXp", user.getGameData().getTotalXp());
        
        return data;
    }
    
    private Map<String, Object> createAttendanceResponseData(User user) {
        Map<String, Object> data = createGameResponseData(user);
        data.put("attendanceStreak", user.getGameData().getAttendanceStreak());
        data.put("lastAttendance", user.getGameData().getLastAttendance());
        return data;
    }
    
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("error", message);
        return error;
    }
} 