package com.gemo.service;

import com.gemo.model.Word;
import com.gemo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final Random random = new Random();

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Word getRandomWord(String difficulty) {
        List<Word> words = wordRepository.findByDifficulty(difficulty);
        if (words.isEmpty()) {
            throw new RuntimeException("No words found for difficulty: " + difficulty);
        }
        return words.get(random.nextInt(words.size()));
    }

    public Word getWord(String word) {
        return wordRepository.findByWord(word);
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Word saveWord(Word word) {
        return wordRepository.save(word);
    }
} 