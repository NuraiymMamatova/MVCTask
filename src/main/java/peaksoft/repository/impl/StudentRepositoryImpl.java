package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudent(Long id, Student student) {
        entityManager.persist(student);
    }

    @Override
    public void deleteStudent(Long id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = entityManager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery("select s from Student  s", Student.class).getResultList();
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        List<Student> students = entityManager.find(Student.class, id).getGroup().getStudents();
        students.forEach(System.out::println);
        return students;
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) throws IOException {
        Student student = entityManager.find(Student.class, studentId);
        Group group = entityManager.find(Group.class, groupId);
        if (group.getStudents() != null) {
            for (Student student1 : group.getStudents()) {
                if (student1.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        group.addStudents(student);
        student.setGroup(group);
        entityManager.merge(student);
        entityManager.merge(group);
    }
}
