package it.university.testapp.dao;

import it.university.testapp.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends CrudRepository<Student, Integer> {

    boolean existsStudentByPassport(long passport);

    boolean existsStudentByPassportAndIdIsNot(long passport, int excludeId);
}
