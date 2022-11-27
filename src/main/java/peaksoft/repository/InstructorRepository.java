package peaksoft.repository;


import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorRepository {

    void saveInstructor(Long id, Instructor instructor);

    void deleteInstructor(Long id);

    void updateInstructor(Long id, Instructor instructor);

    Instructor getInstructorById(Long id);

    List<Instructor> getAllInstructors();

    List<Instructor> getAllInstructors(Long id);

    void assignInstructorToCourse(Long instructorId, Long courseId);

}
