package com.nkemjika.example.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nkemjika.example.studentprofile.StudentProfile;
import com.nkemjika.example.school.School;
import jakarta.persistence.*;

@Entity
//@Table(name="T_STUDENT")
public class Student {

    @Id
    @GeneratedValue
    private int id;

//    @Column(name="f_name", length = 20)
    private String firstName;


    private String lastName;

    @Column(unique = true)
    private String email;

    private int age;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL //this means if I delete student, the student profile is deleted automatically
    )
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference
    private School school;

//    @Column(updatable = false)
////    @Column(updatable = false, insertable = false)
//    private String some_column;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, int age, StudentProfile studentProfile, School school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.studentProfile = studentProfile;
        this.school = school;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
