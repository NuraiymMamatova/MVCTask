package peaksoft.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.*;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.GroupService;

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
        System.out.println("all students repository 1");
        return entityManager.createQuery("select s from Student s where s.group.id = : id",
                Student.class).setParameter("id", id).getResultList();
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) throws IOException {
        System.out.println("assign group to course 1 repository");
        Student student = entityManager.find(Student.class, studentId);
        System.out.println("assign group to course 2 repository");
        Group group = entityManager.find(Group.class, groupId);
        System.out.println("assign group to course 3 repository");
        if (group.getStudents() != null) {
            System.out.println("assign group to course 4 repository");
            for (Student student1 : group.getStudents()) {
                System.out.println("assign group to course 5 repository");
                if (student1.getId() == studentId) {

                    throw new IOException("This student already exists!");
                }
            }
        }
        System.out.println("assign group to course 6 repository");
        group.addStudents(student);
        System.out.println("assign group to course 7 repository");
        student.setGroup(group);
        System.out.println("assign group to course 8 repository");
        entityManager.merge(student);
        System.out.println("assign group to course 9 repository");
        entityManager.merge(group);
        System.out.println("assign group to course 10 repository");
    }

}
