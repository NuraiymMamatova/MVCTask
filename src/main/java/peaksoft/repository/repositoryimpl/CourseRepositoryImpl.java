package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCourse(/*Long id, */Course course) {
  /*      Company company = entityManager.find(Company.class, id);
        company.addCourse(course);
        course.setCompany(company);*/
        entityManager.merge(course);
    }

    @Override
    public void deleteCourse(Long id) {
        entityManager.remove(entityManager.find(Course.class, id));
    }

    @Override
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> getAllCourses(/*Long id*/) {
//        return entityManager.createQuery("select c from Course c where c.company.id = :id", Course.class).getResultList();
        return entityManager.createQuery("select c from Course c ", Course.class).getResultList();
    }
}
