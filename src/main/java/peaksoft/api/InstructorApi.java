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

    @GetMapping("/allOfInstructors")
    private String getAllInstructors(Model model) {
        model.addAttribute("allInstructor", instructorService.getAllInstructors());
        return "/instructor/allInstructors";
    }

    @GetMapping("/new")
    private String newInstructor(Model model) {
        model.addAttribute("newInstructor", new Instructor());
        return "/instructor/saveInstructor";
    }

    @PostMapping("/save")
    private String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/instructor_api/allOfInstructors";
    }

    @GetMapping("/update")
    private String upInstructor(@RequestParam("id") Long id, Model model) {
        model.addAttribute("updateInstructor", instructorService.getInstructorById(id));
        return "/instructor/updateInstructor";
    }

    @PostMapping("/update")
    private String dateInstructor(@ModelAttribute("updateInstructor") Instructor instructor) {
        instructorService.updateInstructor(instructor);
        return "redirect:/instructor_api/allOfInstructors";
    }

    @RequestMapping("/delete")
    private String deleteInstructor(@RequestParam("id") Long id) {
         instructorService.deleteInstructor(id);
         return "redirect:/instructor_api/allOfInstructors";
    }

}
