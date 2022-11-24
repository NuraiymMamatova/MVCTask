package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;

@Controller
@RequestMapping("/instructor_api")
public class InstructorApi {

    private final InstructorService instructorService;

    @Autowired
    public InstructorApi(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/allOfInstructors/{id}")
    private String getAllInstructors(@PathVariable Long id, Model model) {
        model.addAttribute("allInstructor", instructorService.getAllInstructors());
        model.addAttribute("courseId", id);
        return "/instructor/allInstructors";
    }

    @GetMapping("/{id}/new")
    private String newInstructor(@PathVariable Long id,  Model model) {
        model.addAttribute("newInstructor", new Instructor());
        model.addAttribute("courseId", id);
        return "/instructor/saveInstructor";
    }

    @PostMapping("/{id}/save")
    private String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor, @PathVariable Long id) {
        instructorService.saveInstructor(id, instructor);
        return "redirect:/instructor_api/allOfInstructors/" + id;
    }

    @GetMapping("/update/{id}")
    private String upInstructor(@PathVariable("id") Long id, Model model) {
        Instructor instructor =  instructorService.getInstructorById(id);
        model.addAttribute("updateInstructor", instructor);
        model.addAttribute("courseId", instructor.getCourse().getId());
        return "/instructor/updateInstructor";
    }

    @PostMapping("/{courseId}/{id}/update")
    private String dateInstructor(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id, @ModelAttribute("updateInstructor") Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructor_api/allOfInstructors/" + courseId;
    }

    @RequestMapping("/{courseId}/{id}/delete")
    private String deleteInstructor(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id) {
         instructorService.deleteInstructor(id);
         return "redirect:/instructor_api/allOfInstructors/" + courseId;
    }

}
