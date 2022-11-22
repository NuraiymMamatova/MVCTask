package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void deleteStudent(Long id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery("select s from Student  s", Student.class).getResultList();
    }
}
