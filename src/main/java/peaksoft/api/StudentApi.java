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

    @GetMapping("/allOfStudents/{id}")
    private String getAllLessons(@PathVariable Long id, Model model) {
        model.addAttribute("allStudent", studentService.getAllStudents());
        model.addAttribute("groupId", id);
        return "/student/allStudents";
    }

    @GetMapping("/{id}/new")
    private String newStudent(@PathVariable Long id, Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("groupId", id);
        return "/student/saveStudent";
    }

    @PostMapping("/{id}/save")
    private String saveStudent(@ModelAttribute("newStudent") Student student, @PathVariable Long id) {
        studentService.saveStudent(id, student);
        return "redirect:/student_api/allOfStudents/" + id;
    }

    @GetMapping("/update/{id}")
    private String upStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("updateStudent", student);
        model.addAttribute("groupId", student.getGroup().getId());
        return "/student/updateStudent";
    }

    @PostMapping("/{groupId}/{id}/update")
    private String dateStudent(@PathVariable("groupId") Long groupId, @PathVariable("id") Long id, @ModelAttribute("updateStudent") Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/student_api/allOfStudents/" + groupId;
    }

    @RequestMapping("/{groupId}/{id}/delete")
    private String deleteStudent(@PathVariable("groupId") Long groupId, @PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student_api/allOfStudents/" + groupId;
    }
}
