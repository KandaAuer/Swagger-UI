package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private static long studentCounter = 1;
    private final Map<Long, Student> studentRepository;

    public StudentServiceImpl() {
        this.studentRepository = new HashMap<>();
    }

    @Override
    public Student createStudent(Student student) {
        student.setId(studentCounter++);
        studentRepository.put(student.getId(), student);
        return student;
    }

    @Override
    public Student readStudent(long id) {
        return studentRepository.get(id);
    }

    @Override
    public Student updateStudent(Student student) {
        if (!studentRepository.containsKey(student.getId())) {
            return null;
        }
        studentRepository.put(student.getId(), student);
        return student;
    }

    @Override
    public Student deleteStudent(long id) {
        return studentRepository.remove(id);
    }

    @Override
    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : studentRepository.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
