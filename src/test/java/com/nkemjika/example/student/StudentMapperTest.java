package com.nkemjika.example.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

   private StudentMapper studentMapper;

   @BeforeEach
   void setUp() {
       studentMapper = new StudentMapper();
   }

   @Test
   public void shouldMapStudentDtoToStudent(){
    StudentDto dto =  new StudentDto("John", "Doe", "john@gmail.com", 1);
    Student student = studentMapper.toStudent(dto);

    assertEquals(dto.firstName(), student.getFirstName());
    assertEquals(dto.lastName(), student.getLastName());
    assertEquals(dto.email(), student.getEmail());

    //Order here is important as we are sure school id is not null
    assertNotNull(student.getSchool());
    assertEquals(dto.schoolId(), student.getSchool().getId());
   }

   @Test
    public void shouldMapStudentToStudentResponseDto() {
       Student student =  new Student("John", "Doe", "john@gmail.com", 20);

       StudentResponseDto dto = studentMapper.toStudentResponseDto(student);

       assertEquals(dto.firstName(), student.getFirstName());
       assertEquals(dto.lastName(), student.getLastName());
       assertEquals(dto.email(), student.getEmail());

    }
}