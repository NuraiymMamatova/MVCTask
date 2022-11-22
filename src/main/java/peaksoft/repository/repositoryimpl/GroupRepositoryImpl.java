package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
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
    public void saveGroup(Group group) {
        entityManager.merge(group);
    }

    @Override
    public void deleteGroup(Long id) {
        entityManager.remove(entityManager.find(Group.class, id));
    }

    @Override
    public void updateGroup(Group group) {
        entityManager.merge(group);
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getAllGroups() {
        return entityManager.createQuery("select g from Group g", Group.class).getResultList();
    }
}
