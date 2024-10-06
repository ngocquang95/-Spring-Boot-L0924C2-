package com.techzen.academy.repository;

import com.techzen.academy.model.Student;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository { // Dao
    List<Student> students = new ArrayList<>( // Database
            Arrays.asList(
                    new Student(UUID.randomUUID(), "Lê Phước", 8.6),
                    new Student(UUID.randomUUID(), "Quốc Hiếu", 8.9),
                    new Student(UUID.randomUUID(), "Tiến Lợi", 7.5)
            )
    );

    public List<Student> findAll() {
        return students;
    }

    public Student findById(UUID id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Student save(Student student) {
        student.setId(UUID.randomUUID());
        students.add(student);
        return student;
    }
}
