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

    @GetMapping("/allOfCourses")
    private String getAllCourses(Model model/*, @RequestParam("id") Long id*/) {
        model.addAttribute("allCourse", courseService.getAllCourses(/*id*/));
        return "/course/allCourses";
    }

    @GetMapping("/new")
    private String newCourse(Model model) {
        model.addAttribute("newCourse", new Course());
        return "/course/saveCourse";
    }

    @PostMapping("/save")
    private String saveCourse(@ModelAttribute("newCourse") Course course) {
        courseService.saveCourse(course);
        return "redirect:/course_api/allOfCourses";
    }

    @GetMapping("/update")
    private String upCourse(@RequestParam("id") Long id, Model model) {
        model.addAttribute("updateCourse", courseService.getCourseById(id));
        return "/course/updateCourse";
    }

    @PostMapping("/update")
    private String dateCourse(@ModelAttribute("updateCourse") Course course) {
        courseService.updateCourse(course);
        return "redirect:/course_api/allOfCourses";
    }

    @RequestMapping("/delete")
    private String deleteCourse(@RequestParam("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/course_api/allOfCourses";
    }

}
