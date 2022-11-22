package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

@Controller
@RequestMapping("/student_api")
public class StudentApi {

    private final StudentService studentService;

    @Autowired
    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/allOfStudents")
    private String getAllLessons(Model model) {
        model.addAttribute("allStudent", studentService.getAllStudents());
        return "/student/allStudents";
    }

    @GetMapping("/new")
    private String newStudent(Model model) {
        model.addAttribute("newStudent", new Student());
        return "/student/saveStudent";
    }

    @PostMapping("/save")
    private String saveStudent(@ModelAttribute("newStudent") Student student) {
        studentService.saveStudent(student);
        return "redirect:/student_api/allOfStudents";
    }

    @GetMapping("/update")
    private String upStudent(@RequestParam("id") Long id, Model model) {
        model.addAttribute("updateStudent", studentService.getStudentById(id));
        return "/student/updateStudent";
    }

    @PostMapping("/update")
    private String dateStudent(@ModelAttribute("updateStudent") Student student) {
        studentService.updateStudent(student);
        return "redirect:/student_api/allOfStudents";
    }

    @RequestMapping("/delete")
    private String deleteStudent(@RequestParam("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student_api/allOfStudents";
    }
}
