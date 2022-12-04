package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.*;
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
    public void saveCourse(Long id, Course course) {
        Company company = entityManager.find(Company.class, id);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(company);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = entityManager.find(Course.class, id);
        for (Instructor instructor : course.getInstructors()) {
            for (Group group : course.getGroups()) {
                for (Student student : group.getStudents()) {
                    instructor.minus();
                    course.getCompany().minus();
                }
            }
        }
        entityManager.remove(course);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = entityManager.find(Course.class, id);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        entityManager.merge(course1);
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager.createQuery("select c from Course c ", Course.class).getResultList();
    }

    @Override
    public List<Course> getAllCourses(Long id) {
        return entityManager.createQuery("select c from Course c where c.company.id = :id", Course.class).setParameter("id", id).getResultList();
    }

}
