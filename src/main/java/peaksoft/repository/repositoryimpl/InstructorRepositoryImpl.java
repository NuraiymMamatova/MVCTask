package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInstructor(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        entityManager.remove(entityManager.find(Instructor.class, id));
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public void assignInstructorToCourse(Course course, Instructor instructor) {
        Course course1 = entityManager.find(Course.class, course);
        Instructor instructor1 = entityManager.find(Instructor.class, instructor);

    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();
    }
}
