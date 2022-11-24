package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveLesson(Long id, Lesson lesson) {
        Course course = entityManager.find(Course.class, id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        entityManager.merge(course);
    }

    @Override
    public void deleteLesson(Long id) {
        entityManager.remove(entityManager.find(Lesson.class, id));
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class, id);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class, id);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return entityManager.createQuery("select l from Lesson l", Lesson.class).getResultList();
    }
}
