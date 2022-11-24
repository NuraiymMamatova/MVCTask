package peaksoft.service;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {

    void saveCourse(Long id, Course course);

    void deleteCourse(Long id);

    void updateCourse(Long id, Course course);

    Course getCourseById(Long id);

    List<Course> getAllCourses();
}
