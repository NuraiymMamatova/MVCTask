package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInstructor(Long id, Instructor instructor) throws IOException {
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName().replace(" ", ""), instructor.getFirstName().replace(" ", ""));
        entityManager.persist(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        entityManager.remove(entityManager.find(Instructor.class, id));
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) throws IOException {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName().replace(" ", ""), instructor.getFirstName().replace(" ", ""));
        entityManager.merge(instructor1);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();
    }

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return entityManager.createQuery("select i from Instructor i where i.course.id = :id", Instructor.class).setParameter("id", id).getResultList();
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) throws IOException {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Course course = entityManager.find(Course.class, courseId);
        if (course.getInstructors() != null) {
            for (Instructor instructor1 : course.getInstructors()) {
                if (instructor1.getId() == instructorId) {
                    throw  new IOException("This instructor already exists!");
                }
            }
        }

        course.addInstructor(instructor);
        instructor.setCourse(course);
        entityManager.merge(instructor);
        entityManager.merge(course);
    }

    private void validator(String phoneNumber, String firstName, String lastName) throws IOException {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character character : firstName.toCharArray()) {
                if (!Character.isAlphabetic(character)) {
                    throw  new IOException("Numbers cannot be inserted in the name of the instructor");
                }
            }
            for (Character character : lastName.toCharArray()) {
                if (!Character.isAlphabetic(character)) {
                    throw new IOException("Numbers cannot be inserted into the name of the instructor");
                }
            }
        } else {
            throw new IOException("Instructor's first or last name must contain at least 3 letters");
        }

        if (phoneNumber.length() == 13 && phoneNumber.charAt(0) == '+' && phoneNumber.charAt(1) == '9' && phoneNumber.charAt(2) == '9' && phoneNumber.charAt(3) == '6') {
            int counter = 0;

            for (Character character : phoneNumber.toCharArray()) {
                if (counter!= 0) {
                    if (!Character.isDigit(character)) {
                        throw new IOException("Number format is not correct");
                    }
                }
                counter++;
            }
        }else {
            throw new IOException("Number format is not correct");
        }
    }
}
