package com.victoruk.student_management.dashboard.controller;

import com.victoruk.student_management.dashboard.entity.Student;
import com.victoruk.student_management.dashboard.exception.StudentNotFoundException;
import com.victoruk.student_management.dashboard.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StudentViewController {

    private static final Logger log = LoggerFactory.getLogger(StudentViewController.class);
    private final StudentService studentService;

    public StudentViewController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "name", defaultValue = "") String name, Model model) {
        log.info("Fetching students for the home page");
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("studentList", studentList);
        return "homePage";
    }

    @GetMapping("/create")
    public String create(Model model) {
        log.info("Displaying create student form");
        model.addAttribute("student", new Student());
        return "createStudent";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes, Model model) {

        log.info("Attempting to save student: {}", student);
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors occurred: {}", bindingResult.getAllErrors());
            return "createStudent";
        }
        studentService.addStudent(student);
        redirectAttributes.addFlashAttribute(
                "message",
                "Student created successfully"
        );
        log.info("Student saved successfully: {}", student);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        log.info("Fetching student with id {} for editing", id);
        Student student = studentService.findById(id);
        if (student == null) {
            log.error("Student with id {} not found", id);
            return "redirect:/home";
        }
        model.addAttribute("student", student);
        return "updatestudent";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
            Model model) {

        log.info("Updating student with id {} and provided fields", id);

        try {
            studentService.editStudent(id, userName, email, firstName, lastName, phoneNumber, dob);
            log.info("Student updated successfully with id {}", id);
            return "redirect:/home";
        } catch (StudentNotFoundException e) {
            log.error("Student not found: {}", e.getMessage());
            model.addAttribute("error", "Student not found.");
            return "edit";
        } catch (Exception e) {
            log.error("An error occurred while updating the student: {}", e.getMessage());
            model.addAttribute("error", "An unexpected error occurred.");
            return "updatestudent";
        }
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("Deleting student with id {}", id);
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully");
        log.info("Student with id {} deleted successfully", id);
        return "redirect:/home";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        log.info("Fetching details for student with id {}", id);
        Student student = studentService.findById(id);
        if (student == null) {
            log.error("Student with id {} not found", id);
            return "redirect:/home";
        }
        model.addAttribute("student", student);
        return "details";
    }



}
