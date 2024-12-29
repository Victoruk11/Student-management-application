package com.victoruk.student_management.dashboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import jakarta.validation.constraints.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Entity
@Table(name = "students")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    private String userName;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    // Additional fields can be added here


    public Student() {

    }
    public Student(Long id, String userName, String email, String firstName, String lastName, String phoneNumber, LocalDate dob) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                '}';
    }
}
