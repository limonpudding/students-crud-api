package it.university.testapp.service.impl;

import it.university.testapp.entity.Student;
import it.university.testapp.dao.StudentDAO;
import it.university.testapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public Student addStudent(Student student) {
        if (studentDAO.existsStudentByPassport(student.getPassport())) {
            return null;
        } else {
            return studentDAO.save(student);
        }
    }

    @Override
    public Student updateStudentById(Student student, int studentId) {
        if (studentDAO.existsStudentByPassportAndIdIsNot(student.getPassport(), studentId)) {
            return null;
        } else {
            student.setId(studentId);
            return studentDAO.save(student);
        }
    }

    @Override
    public Student getStudent(int studentId) {
        Optional<Student> studentOptional = studentDAO.findById(studentId);
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        studentDAO.deleteById(studentId);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentDAO.findAll();
    }
}
