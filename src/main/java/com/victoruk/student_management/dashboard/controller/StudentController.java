//package com.victoruk.microservices.limits_service.controller;
//
//import com.victoruk.microservices.limits_service.entity.Student;
//import com.victoruk.microservices.limits_service.service.StudentService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/students")
//public class StudentController {
//
//    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
//    private final StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    // Add Student
//    @PostMapping("/add")
//    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
//        log.info("Received request to add student: {}", student);
//        Student savedStudent = studentService.addStudent(student);
//        log.info("Student added successfully: {}", savedStudent);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
//    }
//
//    // Edit Student
//    @PutMapping("/{id}")
//    public ResponseEntity<?> editStudent(@PathVariable Long id,
//                                         @RequestParam(required = false) String userName,
//                                         @RequestParam(required = false) String email,
//                                         @RequestParam(required = false) String firstName,
//                                         @RequestParam(required = false) String lastName,
//                                         @RequestParam(required = false) String phoneNumber,
//                                         @RequestParam(required = false) LocalDate dob) {
//
//            log.info("Request received to update student with ID: {}", id);
//            Student updatedStudent = studentService.editStudent(id, userName, email, firstName, lastName, phoneNumber, dob);
//            log.info("Student updated successfully: {}", updatedStudent);
//            return ResponseEntity.ok(updatedStudent);
//
//    }
//
//
//    // Delete Student
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
//        log.info("Request received to delete student with ID: {}", id);
//        studentService.deleteStudent(id);
//        log.info("Student with ID {} deleted successfully.", id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // Get Student by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
//        log.info("Request received to fetch student with ID: {}", id);
//        Student student = studentService.getStudentById(id);
//        log.info("Student retrieved successfully: {}", student);
//        return ResponseEntity.ok(student);
//    }
//
//    // Get All Students
//    @GetMapping
//    public ResponseEntity<List<Student>> getAllStudents() {
//        log.info("Request received to fetch all students.");
//        List<Student> students = studentService.getAllStudents();
//        log.info("Total students retrieved: {}", students.size());
//        return ResponseEntity.ok(students);
//    }
//}
