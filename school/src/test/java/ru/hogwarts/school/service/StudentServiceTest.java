package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl();
    }

    @Test
    void testCreateStudent() {
        Student student = studentService.createStudent(new Student("Ron", 11));
        assertNotNull(student);
        assertEquals("Ron", student.getName());
        assertEquals(11, student.getAge());
    }

    @Test
    void testReadStudent() {
        Student student = studentService.createStudent(new Student("Draco", 11));
        Student foundStudent = studentService.readStudent(student.getId());
        assertNotNull(foundStudent);
        assertEquals(student.getId(), foundStudent.getId());
    }

    @Test
    void testUpdateStudent() {
        Student student = studentService.createStudent(new Student("Harry", 11));
        student.setName("Harry Potter");
        student.setAge(12);
        Student updatedStudent = studentService.updateStudent(student);
        assertNotNull(updatedStudent);
        assertEquals("Harry Potter", updatedStudent.getName());
        assertEquals(12, updatedStudent.getAge());
    }

    @Test
    void testDeleteStudent() {
        Student student = studentService.createStudent(new Student("Hermione", 11));
        Student deletedStudent = studentService.deleteStudent(student.getId());
        assertNotNull(deletedStudent);
        assertEquals(student.getId(), deletedStudent.getId());
    }

    @Test
    void testFindByAge() {
        studentService.createStudent(new Student("Neville", 12));
        studentService.createStudent(new Student("Luna", 11));
        List<Student> students = (List<Student>) studentService.findByAge(11);
        assertEquals(1, students.size());
        assertEquals("Luna", students.get(0).getName());
    }
}
