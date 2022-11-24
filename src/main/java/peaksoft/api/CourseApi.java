package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;

@Controller
@RequestMapping("/course_api")
public class CourseApi {

    private final CourseService courseService;

    @Autowired
    public CourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/allOfCourses/{id}")
    private String getAllCourses(@PathVariable Long id,  Model model) {
        model.addAttribute("allCourse", courseService.getAllCourses());
        model.addAttribute("companyId", id);
        return "/course/allCourses";
    }

    @GetMapping("/{id}/new")
    private String newCourse(@PathVariable Long id, Model model) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("companyId", id);
        return "/course/saveCourse";
    }

    @PostMapping("/{id}/save")
    private String saveCourse(@ModelAttribute("newCourse") Course course, @PathVariable Long id) {
        courseService.saveCourse(id, course);
        return "redirect:/course_api/allOfCourses/" + id;
    }

    @GetMapping("/update/{id}")
    private String upCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("updateCourse", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/course/updateCourse";
    }

    @PostMapping("/{companyId}/{id}/update")
    private String dateCourse(@PathVariable("companyId")Long companyId, @PathVariable("id") Long id, @ModelAttribute("updateCourse") Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/course_api/allOfCourses/" + companyId;
    }

    @GetMapping("/{companyId}/{id}/deleteCourse")
    private String deleteCourse(@PathVariable("companyId") Long companyId, @PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/course_api/allOfCourses/" + companyId;
    }

}