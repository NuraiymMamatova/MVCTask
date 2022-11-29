package peaksoft.repository;


import peaksoft.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentRepository {

    void saveStudent(Long id, Student student);

    void deleteStudent(Long id);

    void updateStudent(Long id, Student student);

    Student getStudentById(Long id);

    List<Student> getAllStudents();

    List<Student> getAllStudents(Long id);

    void assignStudentToGroup(Long studentId, Long groupId) throws IOException;
}
