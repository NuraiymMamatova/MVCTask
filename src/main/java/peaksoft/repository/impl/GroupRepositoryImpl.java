package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    public void assignGroupToCourse(Long groupId, Long courseId) {
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(course);
    }

}