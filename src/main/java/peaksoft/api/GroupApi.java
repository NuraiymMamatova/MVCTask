package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.service.GroupService;

@Controller
@RequestMapping("/group_api")
public class GroupApi {

    private final GroupService groupService;

    @Autowired
    public GroupApi(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/allOfGroups/{id}")
    private String getAllGroups(@PathVariable Long id, Model model) {
        model.addAttribute("allGroup",  groupService.getAllGroups());
        model.addAttribute("courseId", id);
        return "/group/allGroups";
    }

    @GetMapping("/{id}/new")
    private String newGroup(@PathVariable Long id,  Model model) {
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courseId", id);
        return "/group/saveGroup";
    }

    @PostMapping("/{id}/save")
    private String saveGroup(@ModelAttribute("newGroup") Group group, @PathVariable Long id) {
        groupService.saveGroup(id, group);
        return "redirect:/group_api/allOfGroups/" + id;
    }

    @GetMapping("/update/{courseId}/{id}")
    private String upGroup(@PathVariable("id")Long id, @PathVariable("courseId")Long courseId, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("updateGroup", group);
        model.addAttribute("courseId", courseId);
        return "/group/updateGroup";
    }

    @PostMapping("/{courseId}/{id}/update")
    private String dateGroup(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id, @ModelAttribute("updateGroup") Group group) {
        System.out.println("date group before");
        groupService.updateGroup(id, group);
        System.out.println("date group after");
        return "redirect:/group_api/allOfGroups/" + courseId;
    }

    @RequestMapping("/{courseId}/{id}/delete")
    private String deleteGroup(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id) {
        groupService.deleteGroup(id);
        return "redirect:/group_api/allOfGroups/" + courseId;
    }
}
