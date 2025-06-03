package com.gemogame.gemo.controller;

import com.gemogame.gemo.entity.Test;
import com.gemogame.gemo.repository.TestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
}
