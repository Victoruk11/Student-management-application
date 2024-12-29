package com.victoruk.student_management.dashboard.repository;

import com.victoruk.student_management.dashboard.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository  extends JpaRepository <Student,Long> {


    Optional<Student> findByUserName(String userName);
}
