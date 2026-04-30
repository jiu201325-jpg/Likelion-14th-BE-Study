package com.example.hellospring.dto;

import com.example.hellospring.entity.Student;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class StudentResponse {
    private long id;
    private String name;
    private String email;

    public StudentResponse(long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static StudentResponse from(Student student){
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }
    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
}
