package com.gemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "words")
public class Word {
    @Id
    private String id;
    private String word;
    private String meaning;
    private String difficulty; // EASY, MEDIUM, HARD

    public Word() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
} 