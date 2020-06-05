package it.university.testapp.dao;

import it.university.testapp.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends CrudRepository<Student, Integer> {

    boolean existsStudentByPassport(String passport);

    boolean existsStudentByPassportAndIdIsNot(String passport, int excludeId);

    boolean existsStudentById(int id);
}
