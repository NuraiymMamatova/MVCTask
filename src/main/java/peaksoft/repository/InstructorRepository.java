package peaksoft.repository;


import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorRepository {

    void saveInstructor(Instructor instructor);

    void deleteInstructor(Long id);

    void updateInstructor(Instructor instructor);

    void assignInstructorToCourse(Course course, Instructor instructor);

    Instructor getInstructorById(Long id);

    List<Instructor> getAllInstructors();

}
