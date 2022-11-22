package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

@Controller
@RequestMapping("/task_api")
public class TaskApi {

    private final TaskService taskService;

    @Autowired
    public TaskApi(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allOfTasks")
    private String getAllTasks(Model model) {
        model.addAttribute("allTask", taskService.getAllTasks());
        return "/task/allTasks";
    }

    @GetMapping("/new")
    private String newTask(Model model) {
        model.addAttribute("newTask", new Task());
        return "/task/saveTask";
    }

    @PostMapping("/save")
    private String saveTask(@ModelAttribute("newTask") Task task) {
        taskService.saveTask(task);
        return "redirect:/task_api/allOfTasks";
    }

    @GetMapping("/update")
    private String upTask(@RequestParam("id") Long id, Model model) {
        model.addAttribute("updateTask", taskService.getTaskById(id));
        return "/task/updateTask";
    }

    @PostMapping("/update")
    private String dateTask(@ModelAttribute("updateTask") Task task) {
        taskService.updateTask(task);
        return "redirect:/task_api/allOfTasks";
    }

    @RequestMapping("/delete")
    private String deleteTask(@RequestParam("id")Long id) {
        taskService.deleteTask(id);
        return "redirect:/task_api/allOfTasks";
    }

}
