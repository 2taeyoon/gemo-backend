package com.gemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    
    // 기본 사용자 정보
    @Indexed(unique = true)
    private String email;           // 이메일 (Google OAuth)
    private String name;            // 사용자 이름
    private String picture;         // 프로필 사진 URL
    
    // OAuth 관련
    private String googleId;        // Google OAuth ID
    
    // 게임 데이터
    private GameData gameData = new GameData();
    
    // 메타데이터
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    // 사용자 상태
    private boolean active = true;  // 활성 사용자 여부
} 