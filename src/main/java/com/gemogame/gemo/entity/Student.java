package com.gemogame.gemo.entity; // 폴더 구조, 일반적으로 'com.회사명.프로젝트명.기능' 식으로 구조화

import jakarta.persistence.*; // JPA 관련 기능을 가져옴(@Entity, @Id 등은 여기서 제공됨)

// Lombok이라는 라이브러리를 사용해서
// 자동으로 getter/setter, 생성자를 만들어주는 어노테이션을 가져옴
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// @Entity: 이 클래스와 매핑될 DB 테이블의 이름을 "student"라고 지정해요.
// @Table(name = "student"): Student라는 자바 클래스를 DB 테이블로 매핑하겠다는 의미!
// @Getter: 모든 필드에 대해 자동으로 get메서드 생성 (ex. getName())
// @Setter: 자동으로 set메서드 생성 (ex. setAge())
// @NoArgsConstructor: 기본 생성자(아무 것도 없는 생성자)를 자동으로 만들어줌
@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {
	// 모든 데이터는 고유한 id 값으로 구분돼야 하니까 필수
	// 기본 키 값을 자동으로 증가시키도록 설정
	// MySQL에서는 이걸 AUTO_INCREMENT라고함
	// GenerationType.IDENTITY는 DB가 알아서 증가시켜줌
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 학생의 이름을 저장할 필드예요.
	// 특별한 어노테이션이 없으면, 컬럼명은 필드명과 같고 문자열로 저장돼요.
	private String name;

	// 학생의 나이를 저장할 필드예요.
	// 정수형 숫자로 저장됩니다.
	private Integer age;
}