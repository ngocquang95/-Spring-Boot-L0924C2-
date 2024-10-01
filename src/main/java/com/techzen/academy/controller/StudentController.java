package com.techzen.academy.controller;

import com.techzen.academy.Student;
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

    // @RequestMapping(value = "/students", method = RequestMethod.GET) // Danh tu so nhieu
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") UUID id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.notFound().build(); // 404?
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        student.setId(UUID.randomUUID());
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student); // 404?
    }
}
