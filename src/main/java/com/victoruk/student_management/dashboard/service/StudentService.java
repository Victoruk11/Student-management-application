package com.victoruk.student_management.dashboard.service;

import com.victoruk.student_management.dashboard.entity.Student;
import com.victoruk.student_management.dashboard.exception.StudentConstraintViolationException;
import com.victoruk.student_management.dashboard.exception.StudentNotFoundException;
import com.victoruk.student_management.dashboard.exception.StudentServiceUnExpectedError;
import com.victoruk.student_management.dashboard.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add Student
    public Student addStudent(Student student) {
        try {
            log.info("Attempting to save student: {}", student);
            Student savedStudent = studentRepository.save(student);
            log.info("Student saved successfully: {}", savedStudent);
            return savedStudent;
        } catch (StudentConstraintViolationException e) {
            log.error("Database constraint violation occurred while saving student: {}", student, e);
            throw new StudentConstraintViolationException("Failed to save the student due to database constraint violation.", e);
        } catch (StudentServiceUnExpectedError e) {
            log.error("Unexpected error occurred while saving student: {}", student, e);
            throw new StudentServiceUnExpectedError("An unexpected error occurred while saving the student.", e);
        }
    }

    // Edit Student
    public Student editStudent(Long id, String userName, String email, String firstName, String lastName,
                               String phoneNumber, LocalDate dob ) {
        try {
            log.info("Attempting to update student with ID: {}", id);
            Optional<Student> existingStudentOptional = studentRepository.findById(id);

            if (existingStudentOptional.isEmpty()) {
                log.error("Student with ID {} not found.", id);
                throw new StudentNotFoundException("Student with ID " + id + " not found." );
            }

            Student existingStudent = existingStudentOptional.get();
            // Update fields
            if (userName != null) existingStudent.setUserName(userName);
            if (email != null)  existingStudent.setEmail(email);
            if (firstName != null) existingStudent.setFirstName(firstName);
            if (lastName != null) existingStudent.setLastName(lastName);
            if (phoneNumber != null) existingStudent.setPhoneNumber(phoneNumber);
            if (dob != null)existingStudent.setDob(dob);

            Student savedStudent = studentRepository.save(existingStudent);
            log.info("Student updated successfully: {}", savedStudent);
            return savedStudent;
        } catch (StudentServiceUnExpectedError e) {
            log.error("Unexpected error occurred while updating student with ID: {}", id, e);
            throw new StudentServiceUnExpectedError("An unexpected error occurred while updating the student.", e);
        }
    }

    // Delete Student
    public void deleteStudent(Long id) {
        try {
            log.info("Attempting to delete student with ID: {}", id);
            if (!studentRepository.existsById(id)) {
                log.error("Student with ID {} not found.", id);
                throw new StudentNotFoundException("Student with ID " + id + " not found.");
            }

            studentRepository.deleteById(id);
            log.info("Student with ID {} deleted successfully.", id);
        } catch (StudentServiceUnExpectedError e) {
            log.error("Unexpected error occurred while deleting student with ID: {}", id, e);
            throw new StudentServiceUnExpectedError("An unexpected error occurred while deleting the student.", e);
        }
    }

    // Get Student by ID
    public Student findById(Long id) {
        try {
            log.info("Fetching student with ID: {}", id);
            Optional<Student> studentOptional = studentRepository.findById(id);

            if (studentOptional.isEmpty()) {
                log.error("Student with ID {} not found.", id);
                throw new StudentNotFoundException("Student with ID " + id + " not found.");
            }

            Student student = studentOptional.get();
            log.info("Student retrieved successfully: {}", student);
            return student;
        } catch (StudentServiceUnExpectedError e) {
            log.error("Unexpected error occurred while fetching student with ID: {}", id, e);
            throw new StudentServiceUnExpectedError("An unexpected error occurred while fetching the student.", e);
        }
    }

    // Get All Students
    public List<Student> getAllStudents() {
        try {
            log.info("Fetching all students from the database.");
            List<Student> students = studentRepository.findAll();
            log.info("Total students retrieved: {}", students.size());
            return students;
        } catch (StudentServiceUnExpectedError e) {
            log.error("Unexpected error occurred while fetching all students.", e);
            throw new StudentServiceUnExpectedError("An unexpected error occurred while fetching all students.", e);
        }
    }
}
