package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);
    Student readStudent(long id);
    Student updateStudent(Student student);
    Student deleteStudent(long id);
    Collection<Student> findByAge(int age);
}
