package com.nkemjika.example.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Integer is the data type of the unique ID in the student entity
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstNameContaining(String firstname);
}
