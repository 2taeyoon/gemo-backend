package com.gemogame.gemo;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GemoApplication {

	public static void main(String[] args) {
		// 1. .env 강제 로딩
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// 2. 로딩된 환경변수 → Spring 초기 환경에 주입
		Map<String, Object> props = new HashMap<>();
		props.put("DB_URL", dotenv.get("DB_URL"));
		props.put("DB_USERNAME", dotenv.get("DB_USERNAME"));
		props.put("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication app = new SpringApplication(GemoApplication.class);
		app.setDefaultProperties(props); // 이게 핵심!
		app.run(args);
	}
}
