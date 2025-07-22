package com.gemo.repository;

import com.gemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // 이메일로 사용자 조회
    Optional<User> findByEmail(String email);
    
    // Google ID로 사용자 조회
    Optional<User> findByGoogleId(String googleId);
    
    // 활성 사용자만 조회
    Optional<User> findByEmailAndActiveTrue(String email);
    
    // 이메일 존재 여부 확인
    boolean existsByEmail(String email);
} 