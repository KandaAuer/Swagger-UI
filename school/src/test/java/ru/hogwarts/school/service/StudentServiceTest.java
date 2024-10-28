package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository repository;
    @InjectMocks
    StudentService service;
    Student student = new Student(1L, "Гарри", 11);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new StudentService(repository);
    }
    @Test
    void addStudent(){
        when(repository.save(student)).thenReturn(student);
        Student result = service.addStudent(student);
        assertEquals(student, result);
    }

    @Test
    void findStudent_shouldReturnStudent() {
        when(repository.findById(student.getId()))
                .thenReturn(Optional.of(student));
        Student result = service.findStudent(student.getId());
        assertEquals(student, result);
    }
    @Test
    void editStudent() {
        Mockito.when(repository.findById(student.getId()))
                .thenReturn(Optional.of(student));
        Mockito.when(repository.save(student)).thenReturn(student);
        Student result = service.editStudent(student.getId(), student);
        Mockito.verify(repository).findById(student.getId());
        Mockito.verify(repository).save(student);
        assertEquals(student, result);
    }

    @Test
    public void testRemoveStudent() {
        long id = 1L;
        Student student = new Student(id, "Илья", 25);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(student));
        Student result = service.removeStudent(id);
        Mockito.verify(repository).findById(id);
        Mockito.verify(repository).delete(student);
        assertEquals(student, result);
    }

    @Test
    void findByAge() {
        int age = 25;
        Collection<Student> students = new ArrayList<>();
        students.add(new Student(1L, "Илья", age));
        students.add(new Student(2L, "Алиса", age));
        Mockito.when(repository.findAllByAge(age)).thenReturn(students);
        Collection<Student> result = service.findByAge(age);
        Mockito.verify(repository).findAllByAge(age);
        assertEquals(students, result);
    }
}
