package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import peaksoft.service.InstructorService;

import java.io.IOException;

@Controller
@RequestMapping("/course_api")
public class CourseApi {

    private final CourseService courseService;

    private final GroupService groupService;

    private final InstructorService instructorService;

    @Autowired
    public CourseApi(CourseService courseService, GroupService groupService, InstructorService instructorService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.instructorService = instructorService;
    }

    @GetMapping("/allOfCourses/{id}")
    private String getAllCourses(@PathVariable Long id,  Model model) {
        model.addAttribute("allCourse", courseService.getAllCourses());
        model.addAttribute("companyId", id);
        return "/course/allCourses";
    }

    @GetMapping("/allOfCoursess/{id}")
    private String getAllCoursess(@PathVariable Long id, @ModelAttribute("group") Group group, @ModelAttribute("instructor") Instructor instructor, Model model) {
        model.addAttribute("myAllCourse", courseService.getAllCourses(id));
        model.addAttribute("companyId", id);
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("instructors", instructorService.getAllInstructors());
        return "/course/allCoursesById";
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
        return "redirect:/course_api/allOfCoursess/" + id;
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
        return "redirect:/course_api/allOfCoursess/" + companyId;
    }

    @GetMapping("/{companyId}/{id}/deleteCourse")
    private String deleteCourse(@PathVariable("companyId") Long companyId, @PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/course_api/allOfCoursess/" + companyId;
    }

    @PostMapping("/{courseId}/assignGroup")
    private String assignGroup(@PathVariable Long courseId, @ModelAttribute("group") Group group) {
        groupService.assignGroupToCourse(courseId, group.getId());
        return "redirect:/allOfCoursess/" + courseId;
    }

    @PostMapping("/{courseId}/assignInstructor")
    private String assignInstructorToCourse(@PathVariable("courseId") Long courseId, @ModelAttribute("instructor")Instructor instructor) throws IOException {
        instructorService.assignInstructorToCourse(instructor.getId(), courseId);
        return "redirect:/allOfCoursess/" + courseId;
    }

}
