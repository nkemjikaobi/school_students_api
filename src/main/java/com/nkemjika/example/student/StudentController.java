package com.nkemjika.example.student;

import com.nkemjika.example.Order;
import com.nkemjika.example.OrderRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Hello from my first controllerrrr";
//    }

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/post")
    public String post(@RequestBody String message){
        return "Request accepted and message is : " + message;
    }

    @PostMapping("/post-order")
    public String postOrder(@RequestBody Order order){
        return "Request accepted and order is : " + order.toString();
    }

    @PostMapping("/post-order-record")
    public String postOrderRecord(@RequestBody OrderRecord order){
        return "Request accepted and order is : " + order.toString();
    }

    //http://locahost:8080/hello/nkem
    @GetMapping("/hello/{user-name}")
    public String pathVar(@PathVariable("user-name") String userName) {
        return "my value = " + userName;
    }

    //http://locahost:8080/hello?param_name=pramValue&param_name=2
    @GetMapping("/hello")
    public String paramVar(
            @RequestParam("user-name") String userName,
            @RequestParam("user-lastname") String userLastName
                           ) {
        return "my value = " + userName + "  " + userLastName;
    }

    @PostMapping("/students")
    public StudentResponseDto createStudent(@RequestBody StudentDto dto){
        return studentService.createStudent(dto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(@PathVariable("student-id") Integer studentId) {
        return studentService.findStudentById(studentId);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentsByName(@PathVariable("student-name") String name) {
        return studentService.findStudentsByName(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer studentId) {
        studentService.deleteStudent(studentId);
    }

}
