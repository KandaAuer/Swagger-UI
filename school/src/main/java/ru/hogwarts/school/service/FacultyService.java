package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);
    Faculty readFaculty(long id);
    Faculty updateFaculty(Faculty faculty);
    Faculty deleteFaculty(long id);
    Collection<Faculty> findByColor(String color);
}
