package peaksoft.service;

import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {

    void saveTask(Long id, Task task);

    void deleteTask(Long id);

    void updateTask(Long id, Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks();
}
