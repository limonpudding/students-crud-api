package it.university.testapp;

import it.university.testapp.entity.Student;
import it.university.testapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student resultStudent = studentService.addStudent(student);
        if (resultStudent == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(student);
        }
    }

    @RequestMapping(value = "/student/{studentId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        Student resultStudent = studentService.updateStudentById(student, studentId);
        if (resultStudent == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(student);
        }
    }

    @RequestMapping(value = "/student/{studentId}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") int studentId) {
        Student resultStudent = studentService.getStudent(studentId);
        if (resultStudent == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(resultStudent);
        }
    }

    @RequestMapping(value = "/students",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @RequestMapping(value = "/student/{studentId}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Student> deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
