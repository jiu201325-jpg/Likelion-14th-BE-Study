package com.example.hellospring.controller;

import com.example.hellospring.dto.StudentJoinRequest;
import com.example.hellospring.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hellospring.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/api/students")
    public ResponseEntity<String> register(@RequestBody StudentJoinRequest request) {
        studentService.join(request); // 등록 처리 - Service한테 맡김.
        return ResponseEntity.ok("등록 성공!");
    }

    @GetMapping("/api/students")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/api/students/email")
    public ResponseEntity<?> getStudentByEmail(@RequestParam String email){
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }
}