package it.university.testapp.service;

import it.university.testapp.entity.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    Student updateStudentById(Student student, int id);

    Student getStudent(int studentId);

    void deleteStudent(int studentId);

    List<Student> getStudents();
}
