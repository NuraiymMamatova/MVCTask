package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(Long id, Student student) throws IOException {
        studentRepository.saveStudent(id, student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }

    @Override
    public void updateStudent(Long id, Student student) throws IOException {
        studentRepository.updateStudent(id, student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return studentRepository.getAllStudents(id);
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) throws IOException {
        studentRepository.assignStudentToGroup(studentId, groupId);
    }
}
