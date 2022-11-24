package peaksoft.repository.repositoryimpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Lesson;
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
    public void saveTask(Long id, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        lesson.addTasks(task);
        task.setLesson(lesson);
        entityManager.merge(lesson);
    }

    @Override
    public void deleteTask(Long id) {
        entityManager.remove(entityManager.find(Task.class, id));
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1 = entityManager.find(Task.class, id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        entityManager.merge(task1);
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
