package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {

    private FacultyService facultyService;

    @BeforeEach
    void setUp() {
        facultyService = new FacultyServiceImpl();
    }

    @Test
    void testCreateFaculty() {
        Faculty faculty = facultyService.createFaculty(new Faculty("Gryffindor", "Red"));
        assertNotNull(faculty);
        assertEquals("Gryffindor", faculty.getName());
    }

    @Test
    void testReadFaculty() {
        Faculty faculty = facultyService.createFaculty(new Faculty("Slytherin", "Green"));
        Faculty foundFaculty = facultyService.readFaculty(faculty.getId());
        assertNotNull(foundFaculty);
        assertEquals(faculty.getId(), foundFaculty.getId());
    }

    @Test
    void testUpdateFaculty() {
        Faculty faculty = facultyService.createFaculty(new Faculty("Hufflepuff", "Yellow"));
        faculty.setName("Hufflepuff Updated");
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        assertNotNull(updatedFaculty);
        assertEquals("Hufflepuff Updated", updatedFaculty.getName());
    }

    @Test
    void testDeleteFaculty() {
        Faculty faculty = facultyService.createFaculty(new Faculty("Ravenclaw", "Blue"));
        Faculty deletedFaculty = facultyService.deleteFaculty(faculty.getId());
        assertNotNull(deletedFaculty);
        assertEquals(faculty.getId(), deletedFaculty.getId());
    }

    @Test
    void testFindByColor() {
        facultyService.createFaculty(new Faculty("Hufflepuff", "Yellow"));
        facultyService.createFaculty(new Faculty("Slytherin", "Green"));
        Collection<Faculty> faculties = facultyService.findByColor("Yellow");
        assertEquals(1, faculties.size());
        assertEquals("Hufflepuff", faculties.iterator().next().getName());
    }
}
