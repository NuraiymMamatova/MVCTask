package peaksoft.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Task;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.saveTask(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
}
