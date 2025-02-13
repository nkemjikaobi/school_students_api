package com.nkemjika.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    public void should_successfully_fetch_all_students(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "john@gmail.com", 20));

        //Mock the calls
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@gmail.com"
                ));

        //When
        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        //Then
        assertEquals(students.size(), responseDtos.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void should_successfully_find_student_by_id() {
        //Given
        Integer studentId = 1;
        Student student =  new Student("John", "Doe", "john@gmail.com", 20);

        //Mock the calls
        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@gmail.com"
                ));

        //When
        StudentResponseDto studentResponseDto = studentService.findStudentById(studentId);

        //Then
        assertEquals(studentResponseDto.firstName(), student.getFirstName());
        assertEquals(studentResponseDto.lastName(), student.getLastName());
        assertEquals(studentResponseDto.email(), student.getEmail());

        Mockito.verify(repository, Mockito.times(1)).findById(studentId);
    }

    @Test
    public void should_successfully_find_student_by_name() {
        //Given
        String name = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "john@gmail.com", 20));

        //Mock the calls
        when(repository.findAllByFirstNameContaining(name)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@gmail.com"
                ));

        //When
        List<StudentResponseDto> studentResponseDto = studentService.findStudentsByName(name);

        //Then
        assertEquals(students.size(), studentResponseDto.size());
        Mockito.verify(repository, Mockito.times(1)).findAllByFirstNameContaining(name);

    }
}