package com.gemo.controller;

import com.gemo.model.Word;
import com.gemo.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/random")
    public ResponseEntity<Word> getRandomWord(@RequestParam(defaultValue = "EASY") String difficulty) {
        return ResponseEntity.ok(wordService.getRandomWord(difficulty));
    }

    @GetMapping("/{words}")
    public ResponseEntity<Word> getWord(@PathVariable String word) {
        Word foundWord = wordService.getWord(word);
        return foundWord != null ? ResponseEntity.ok(foundWord) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Word>> getAllWords() {
        return ResponseEntity.ok(wordService.getAllWords());
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        return ResponseEntity.ok(wordService.saveWord(word));
    }
} 