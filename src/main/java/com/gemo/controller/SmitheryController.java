package com.gemo.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api/smithery")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class SmitheryController {
    // 실제 요약 기능이 있는 MCP 서버 endpoint로 변경
    private final String endpoint = "https://glama.ai/mcp/servers/k6vhiu27q7/api";
    //private final String apiKey = ""; // 필요시 입력

    @PostMapping
    public ResponseEntity<?> callSmithery(@RequestBody Map<String, Object> input) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = new HashMap<>();
        body.put("block", "summarize_text");
        // input: { content: "요약할 문장", type: "plain" }
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("content", input.get("text"));
        inputMap.put("type", "plain");
        body.put("input", inputMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.setBearerAuth(apiKey); // 필요시 주석 해제

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(endpoint, request, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
} 