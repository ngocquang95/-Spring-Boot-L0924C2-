package com.techzen.academy.controller;

import com.techzen.academy.Student;
import com.techzen.academy.dto.ApiResponse;
import com.techzen.academy.exception.ApiException;
import com.techzen.academy.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(UUID.randomUUID(), "Lê Phước", 8.6),
                    new Student(UUID.randomUUID(), "Quốc Hiếu", 8.9),
                    new Student(UUID.randomUUID(), "Tiến Lợi", 7.5)
            )
    );

    @GetMapping
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(ApiResponse.builder()
                .data(students)
                .build());
    }

    // code - message - data
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") UUID id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return ResponseEntity.ok(ApiResponse.<Student>builder()
                        .data(student)
                        .build());
            }
        }

        throw new ApiException(ErrorCode.STUDENT_NOT_EXIST);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        student.setId(UUID.randomUUID());
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .data(student)
                .build());
    }
}
