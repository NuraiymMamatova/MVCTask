package peaksoft.service;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {

    void saveStudent(Student student);

    void deleteStudent(Long id);

    void updateStudent(Student student);

    Student getStudentById(Long id);

    List<Student> getAllStudents();
}
