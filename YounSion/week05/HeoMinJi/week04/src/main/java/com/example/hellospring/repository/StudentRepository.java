package com.example.hellospring.repository;

import com.example.hellospring.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}

// DB 담당 창구
// Student를 DB에 저장
// 이메일로 스튜던트 조회