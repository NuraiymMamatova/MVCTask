package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

@Controller
@RequestMapping("/lesson_api")
public class LessonApi {

    private final LessonService lessonService;

    @Autowired
    public LessonApi(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/allOfLessons")
    private String getAllLessons(Model model) {
        model.addAttribute("allLesson", lessonService.getAllLessons());
        return "/lesson/allLessons";
    }

    @GetMapping("/new")
    private String newLesson(Model model) {
        model.addAttribute("newLesson", new Lesson());
        return "/lesson/saveLesson";
    }

    @PostMapping("/save")
    private String saveLesson(@ModelAttribute("newLesson") Lesson lesson) {
        lessonService.saveLesson(lesson);
        return "redirect:/lesson_api/allOfLessons";
    }

    @GetMapping("/update")
    private String upLesson(@RequestParam("id") Long id, Model model) {
        model.addAttribute("updateLesson", lessonService.getLessonById(id));
        return "/lesson/updateLesson";
    }

    @PostMapping("/update")
    private String dateLesson(@ModelAttribute("updateLesson") Lesson lesson) {
        lessonService.updateLesson(lesson);
        return "redirect:/lesson_api/allOfLessons";
    }

    @RequestMapping("/delete")
    private String deleteLesson(@RequestParam("id") Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lesson_api/allOfLessons";
    }
}
