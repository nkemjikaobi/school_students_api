package com.nkemjika.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    // which service do we want to test
    @InjectMocks
    private StudentService studentService;

    // declare the dependencies

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_a_student() {

        //Given
        StudentDto dto =  new StudentDto("John", "Doe", "john@gmail.com", 1);
        Student student =  new Student("John", "Doe", "john@gmail.com", 20);
        Student savedStudent =  new Student("John", "Doe", "john@gmail.com", 20);

        savedStudent.setId(1);

        //Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn((savedStudent));
        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto(
                "John", "Doe", "john@gmail.com"));

        //When
        StudentResponseDto responseDto =  studentService.createStudent(dto);

        //Then
        assertEquals(dto.firstName(), responseDto.firstName());
        assertEquals(dto.lastName(), responseDto.lastName());
        assertEquals(dto.email(), responseDto.email());

        Mockito.verify(studentMapper, Mockito.times(1)).toStudent(dto);
        Mockito.verify(repository, Mockito.times(1)).save(student);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponseDto(savedStudent);
    }


}