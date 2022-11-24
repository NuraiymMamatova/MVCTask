package peaksoft.repository.repositoryimpl;

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
        Course course = entityManager.find(Course.class, id);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(course);
    }

    @Override
    public void deleteGroup(Long id) {
        entityManager.remove(entityManager.find(Group.class, id));
    }

    @Override
    public void updateGroup(Long id, Group group) {
        System.out.println("repository 1");
        Group group1 = getGroupById(id);
        System.out.println("repository 2");
        group1.setGroupName(group.getGroupName());
        System.out.println("repository 3");
        group1.setDateOfStart(group.getDateOfStart());
        System.out.println("repository 4");
        group1.setImage(group.getImage());
        System.out.println("repository 5");
        entityManager.merge(group1);
        System.out.println("repository 6");
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getAllGroups() {
        return entityManager.createQuery("select g from Group g", Group.class).getResultList();
    }

    public void getidwithotherid(Long id, List<Course> courses) {

    }

}
