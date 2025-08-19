package com.gemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

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
    private String naverId;         // Naver OAuth ID
    
    // 게임 데이터
    private GameData gameData = new GameData();
    
    // 메타데이터
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    // 사용자 상태
    private boolean active = true;  // 활성 사용자 여부
    
    // 기본 생성자
    public User() {
    }
    
    // 전체 생성자
    public User(String id, String email, String name, String picture, String googleId, String naverId,
                GameData gameData, LocalDateTime createdAt, LocalDateTime updatedAt, boolean active) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.googleId = googleId;
        this.naverId = naverId;
        this.gameData = gameData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }
    
    // Getter 메서드들
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPicture() { return picture; }
    public String getGoogleId() { return googleId; }
    public String getNaverId() { return naverId; }
    public GameData getGameData() { return gameData; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isActive() { return active; }
    
    // Setter 메서드들
    public void setId(String id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setPicture(String picture) { this.picture = picture; }
    public void setGoogleId(String googleId) { this.googleId = googleId; }
    public void setNaverId(String naverId) { this.naverId = naverId; }
    public void setGameData(GameData gameData) { this.gameData = gameData; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setActive(boolean active) { this.active = active; }
} 