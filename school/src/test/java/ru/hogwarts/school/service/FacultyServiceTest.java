package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class FacultyServiceTest {
    @Mock
    FacultyRepository faculties;
    @InjectMocks
    FacultyService service;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new FacultyService(faculties);
    }
    Faculty faculty = new Faculty(1L, "Грифиндор", "Золотой");

    @Test
    void addFaculty() {
        when(faculties.save(faculty))
                .thenReturn(faculty);
        Faculty result = service.addFaculty((faculty));
        assertEquals(faculty, result);
    }

    @Test
    void findFaculty_shouldReturnFaculty() {
        when(faculties.findById(faculty.getId()))
                .thenReturn((Optional.of(faculty)));
        Faculty result = service.findFaculty(faculty.getId());
        assertEquals(faculty, result);
    }

    @Test
    public void testEditFaculty() {
        long id = 1L;
        Faculty faculty = new Faculty(id, "Наука", "Синий");

        Mockito.when(faculties.findById(id)).thenReturn(Optional.of(faculty));

        Mockito.when(faculties.save(faculty)).thenReturn(faculty);

        Faculty result = service.editFaculty(id, faculty);

        Mockito.verify(faculties).findById(id);
        Mockito.verify(faculties).save(faculty);

        assertEquals(faculty, result);
    }

    @Test
    public void testRemoveFaculty() {
        long id = 1L;
        Faculty faculty = new Faculty(id, "Наука", "Синий");
        Mockito.when(faculties.findById(id)).thenReturn(Optional.of(faculty));
        Mockito.doNothing().when(faculties).delete(faculty);
        Faculty result = service.removeFaculty(id);
        Mockito.verify(faculties).delete(faculty);
        Mockito.verify(faculties).findById(id);
        assertEquals(faculty, result);
    }

    @Test
    public void testFindByColor() {
        String color = "Blue";
        Collection<Faculty> facultiesList = new ArrayList<>();
        facultiesList.add(new Faculty(1L, "Наука", color));
        facultiesList.add(new Faculty(2L, "Искусство", color));
        Mockito.when(faculties.findAllByColor(color)).thenReturn(facultiesList);
        Collection<Faculty> result = service.findByColor(color);
        Mockito.verify(faculties).findAllByColor(color);
        assertEquals(facultiesList, result);
    }
}
