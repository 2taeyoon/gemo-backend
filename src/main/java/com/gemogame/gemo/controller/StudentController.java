// 이 파일이 어디 폴더에 있는지 알려주는 거야
// controller는 '요청 받는 곳'을 모아놓은 폴더라고 보면 돼
package com.gemogame.gemo.controller;

// Student는 학생 정보를 담고 있는 엔티티 클래스지
import com.gemogame.gemo.entity.Student;

// StudentRepository는 DB랑 연결해주는 도구야. 여기에 저장, 조회하는 기능이 다 들어 있어
import com.gemogame.gemo.repository.StudentRepository;

// 이건 스프링에서 제공하는 기능인데,
// 이 클래스를 '컨트롤러'라고 알려주는 거야.
// 즉, 외부에서 들어오는 요청을 받아서 처리하는 클래스라는 뜻이지
import org.springframework.web.bind.annotation.*;

import java.util.List; // 리스트 타입 (학생 여러 명 담을 때 써)

// 이 클래스는 REST API를 처리하는 컨트롤러야
// JSON으로 주고받는 API를 만든다고 보면 돼
@RestController

// 요청 URL이 "/api/student"로 시작하면 여기서 처리하겠다는 뜻이야
@RequestMapping("/api/student")
public class StudentController {

    // StudentRepository를 담을 변수야. DB랑 연결된 도구라고 생각하면 돼
    private final StudentRepository studentRepository;

    // 생성자(Constructor)야. StudentController가 실행될 때
    // StudentRepository를 외부에서 받아서 이 클래스 안에서 쓸 수 있게 만들어주는 거지
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // --------------------------
    // 💡 GET 요청: 전체 학생 목록 조회
    // --------------------------

    // 클라이언트가 GET 방식으로 /api/student로 요청하면 실행돼
    // 모든 학생 데이터를 List(목록) 형태로 가져오는 거야
    @GetMapping
    public List<Student> getAllStudents() {
        // DB에 있는 모든 학생 데이터를 가져와서 그대로 리턴해줘
        return studentRepository.findAll();
    }

    // --------------------------
    // 💡 POST 요청: 새로운 학생 추가
    // --------------------------

    // 클라이언트가 POST 방식으로 /api/student로 요청하면 실행돼
    // 요청 본문(body)에 담긴 학생 정보를 읽어서 DB에 저장하는 거야
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        // 받은 학생 정보를 저장하고, 저장된 결과를 그대로 리턴해줘
        return studentRepository.save(student);
    }
}
