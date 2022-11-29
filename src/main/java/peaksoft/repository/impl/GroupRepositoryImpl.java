package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
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
        entityManager.persist(group);
    }

    @Override
    public void deleteGroup(Long id) {
        entityManager.remove(entityManager.find(Group.class, id));
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
        System.out.println("assign group to course 1 repository");
        Group group = entityManager.find(Group.class, groupId);
        System.out.println("assign group to course 2 repository");
        Course course = entityManager.find(Course.class, courseId);
        System.out.println("assign group to course 3 repository");
        if (course.getGroups() != null) {
            System.out.println("assign group to course 4 repository");
            for (Group group1 : course.getGroups()) {
                System.out.println("assign group to course 5 repository");
                if (group1.getId() == groupId) {
                    System.out.println("assign group to course 6 repository");
                    throw  new IOException("This group already exists!");
                }
            }
        }
        System.out.println("assign group to course 7 repository");
        course.addGroup(group);
        System.out.println("assign group to course 8 repository");
        group.addCourse(course);
        System.out.println("assign group to course 9 repository");
        entityManager.merge(course);
        System.out.println("assign group to course 10 repository");
    }

}