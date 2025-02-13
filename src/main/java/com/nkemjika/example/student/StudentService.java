package com.nkemjika.example.student;

import com.nkemjika.example.school.School;
import com.nkemjika.example.school.SchoolDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    private SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto createStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDto((savedStudent));
    }

    public List<StudentResponseDto> findAllStudents() {
        return repository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findStudentById(Integer studentId) {
        return repository.findById(studentId)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);

        //My own solution below
//        var student = repository.findById(studentId).orElse(new Student());
//        return studentMapper.toStudentResponseDto(student);
    }

    public List<StudentResponseDto> findStudentsByName(String name) {
        return repository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Integer studentId) {
        repository.deleteById(studentId);
    }

}
