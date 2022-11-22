package peaksoft.repository;


import peaksoft.entity.Student;

import java.util.List;

public interface StudentRepository {

    void saveStudent(Student student);

    void deleteStudent(Long id);

    void updateStudent(Student student);

    Student getStudentById(Long id);

    List<Student> getAllStudents();
}
