package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Task;
import peaksoft.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveTask(Task task) {
        entityManager.merge(task);
    }

    @Override
    public void deleteTask(Long id) {
        entityManager.remove(entityManager.find(Task.class, id));
    }

    @Override
    public void updateTask(Task task) {
        entityManager.merge(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getAllTasks() {
        return entityManager.createQuery("select t from Task t", Task.class).getResultList();
    }
}
