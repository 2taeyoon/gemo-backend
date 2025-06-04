// 여긴 이 파일이 있는 위치(폴더 구조)를 나타내는 거야
// 보통 'repository' 폴더엔 DB랑 연결되는 파일들을 넣어
package com.gemogame.gemo.repository;

// Student 클래스는 엔티티(학생 정보)였지? 그걸 불러오는 거야
import com.gemogame.gemo.entity.Student;

// JpaRepository는 스프링에서 제공하는 인터페이스인데,
// DB에서 데이터 꺼내고 저장하고 삭제하는 기능들을 아주 쉽게 쓸 수 있게 도와줘
import org.springframework.data.jpa.repository.JpaRepository;

// 이건 Student 엔티티를 위한 레포지토리야
// JpaRepository<Student, Long> 이 뜻은:
// → Student라는 테이블에 접근할 거고,
// → 그 테이블의 기본키(id)의 타입은 Long이야
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 지금은 아무 메서드 없어도 돼
    // 왜냐면 JpaRepository가 기본적인 거 다 만들어줘
    // 예를 들면: findAll(), save(), deleteById(), findById() 같은 것들

    // 나중에 필요하면 여기 안에 직접 메서드 추가해서
    // 예: 이름으로 찾기 같은 것도 할 수 있어
    // List<Student> findByName(String name); ← 이런 식으로
}
