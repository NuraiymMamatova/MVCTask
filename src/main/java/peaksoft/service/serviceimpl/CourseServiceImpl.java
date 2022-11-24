package peaksoft.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveCourse(Long id, Course course) {
        courseRepository.saveCourse(id, course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteCourse(id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        courseRepository.updateCourse(id, course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }
}
