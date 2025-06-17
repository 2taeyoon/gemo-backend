package com.gemo.repository;

import com.gemo.model.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {
    List<Word> findByDifficulty(String difficulty);
    Word findByWord(String word);
} 