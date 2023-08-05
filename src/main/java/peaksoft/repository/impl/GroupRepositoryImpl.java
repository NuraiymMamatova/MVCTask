package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.entity.Student;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGroup(Long id, Group group) {
        Course course = entityManager.find(Course.class, id);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.persist(group);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = entityManager.find(Group.class, id);
        List<Student> students = group.getStudents();
        Long count = students.stream().count();
        for (Course course : group.getCourses()) {
            Long count1 = course.getCompany().getCount();
            count1 -= count;
            course.getCompany().setCount(count1);
            for (Instructor instructor : course.getInstructors()) {
                Long count2 = instructor.getCount();
                count2 -= count;
                instructor.setCount(count2);
            }
        }
        entityManager.remove(group);
    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = getGroupById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getAllGroups() {
        return entityManager.createQuery("select g from Group g", Group.class).getResultList();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        List<Group> groups = entityManager.find(Course.class, courseId).getGroups();
        groups.forEach(System.out::println);
        return groups;
    }

    @Override
    public void assignGroupToCourse(Long groupId, Long courseId) throws IOException {
        Course course = entityManager.find(Course.class, courseId);
        Group group = entityManager.find(Group.class, groupId);
        if (course.getGroups() != null) {
            for (Group group1 : course.getGroups()) {
                if (group1.getId() == groupId) {
                    throw new IOException("This group already exists!");
                }
            }
        }
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(course);
    }

}
