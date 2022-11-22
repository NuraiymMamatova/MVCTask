package peaksoft.service;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {

    void saveInstructor(Instructor instructor);

    void deleteInstructor(Long id);

    void updateInstructor(Instructor instructor);

    Instructor getInstructorById(Long id);

    List<Instructor> getAllInstructors();
}
