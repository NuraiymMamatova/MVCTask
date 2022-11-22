package peaksoft.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepository.saveInstructor(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteInstructor(id);
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        instructorRepository.updateInstructor(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getInstructorById(id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }
}
