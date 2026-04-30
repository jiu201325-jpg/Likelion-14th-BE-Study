package com.example.hellospring.service;

import com.example.hellospring.dto.StudentJoinRequest;
import com.example.hellospring.dto.StudentResponse;
import com.example.hellospring.entity.Student;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.hellospring.repository.StudentRepository;

import java.util.List;

@Service // 서비스 계층
@RequiredArgsConstructor // 롬복이 생성자 자동으로 만듦.
@Transactional(readOnly = true) // 기본 트랙잭션 설정
public class StudentService {
    // Service가 repository를 사용하기 위해 선언한 필드
    private final StudentRepository studentRepository;

    // 학생 등록 처리 메서드
    @Transactional
    public Long join(StudentJoinRequest request){
        // 1. 중복 검증 로직(Service의 역할)
        validateDuplicateStudent(request.getEmail());

        //2. 저장 및 식별자 반환
        Student student = request.toEntity();
        return studentRepository.save(student).getId();
    }

    // 중복 검사
    private void validateDuplicateStudent(String email){
        studentRepository.findByEmail(email)
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    public List<StudentResponse> getAllStudents(){
        return studentRepository.findAll().stream().map(StudentResponse::from).toList();
    }

    public StudentResponse getStudentByEmail(String email){
        Student student = studentRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("해당 이메일을 가진 학생이 존재하지 않습니다."));
        return StudentResponse.from(student);
    }
}