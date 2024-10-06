package com.techzen.academy.controller;

import com.techzen.academy.dto.ApiResponse;
import com.techzen.academy.exception.ApiException;
import com.techzen.academy.exception.ErrorCode;
import com.techzen.academy.model.Student;
import com.techzen.academy.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/students")
// new StudentController // default - singleton
public class StudentController { // Servlet
    IStudentService studentService; // inject

    @GetMapping
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(ApiResponse.builder()
                .data(studentService.findAll())
                .build());
    }

    // code - message - data
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") UUID id) {
        Student student = studentService.findById(id);
        if (student != null) {
            return ResponseEntity.ok(ApiResponse.<Student>builder()
                    .data(student)
                    .build());
        }

        // xu ly => service // phuc tap
        throw new ApiException(ErrorCode.STUDENT_NOT_EXIST); // case basic
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .data(student)
                .build());
    }

    @Bean // bean spring quan ly
    public Student getStudent() {
        return new Student();
    }
}
