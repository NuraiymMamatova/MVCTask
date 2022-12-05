package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudent(Long id, Student student) throws IOException {
       Group group = entityManager.find(Group.class, id);

       for (Course c : group.getCourses()) {
            c.getCompany().plus();
        }

        for (Course c : group.getCourses()) {
            for (Instructor i : c.getInstructors()) {
                i.plus();
            }
        }
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        group.addStudents(student);
        student.setGroup(group);
        entityManager.persist(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        Student studentById = getStudentById(id);
        for (Course course : studentById.getGroup().getCourses()) {
            course.getCompany().minus();

        }
        for (Course course : studentById.getGroup().getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.minus();
            }
        }
        entityManager.remove(student);
    }

    @Override
    public void updateStudent(Long id, Student student) throws IOException {
        Student student1 = entityManager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        entityManager.merge(student1);
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery("select s from Student  s", Student.class).getResultList();
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return entityManager.createQuery("select s from Student s where s.group.id = : id",
                Student.class).setParameter("id", id).getResultList();
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) throws IOException {
        Student student = entityManager.find(Student.class, studentId);
        Group group = entityManager.find(Group.class, groupId);
        if (group.getStudents() != null) {
            for (Student student1 : group.getStudents()) {
                if (student1.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        for (Course course : group.getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.plus();
            }
        }
        group.addStudents(student);
        student.setGroup(group);
        entityManager.merge(student);
        entityManager.merge(group);
    }

    private void validator(String phoneNumber, String firstName, String lastName) throws IOException {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character character : firstName.toCharArray()) {
                if (!Character.isAlphabetic(character)) {
                    throw  new IOException("Numbers cannot be inserted in the name of the student");
                }
            }
            for (Character character : lastName.toCharArray()) {
                if (!Character.isAlphabetic(character)) {
                    throw new IOException("Numbers cannot be inserted into the name of the student");
                }
            }
        } else {
            throw new IOException("Student's first or last name must contain at least 3 letters");
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
