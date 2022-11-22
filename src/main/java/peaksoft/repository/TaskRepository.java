package peaksoft.repository;

import peaksoft.entity.Task;

import java.util.List;

public interface TaskRepository {

    void saveTask(Task task);

    void deleteTask(Long id);

    void updateTask(Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks();
}
