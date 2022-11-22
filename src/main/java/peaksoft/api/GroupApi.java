package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/allOfGroups")
    private String getAllGroups(Model model) {
        model.addAttribute("allGroup",  groupService.getAllGroups());
        return "/group/allGroups";
    }

    @GetMapping("/new")
    private String newGroup(Model model) {
        model.addAttribute("newGroup", new Group());
        return "/group/saveGroup";
    }

    @PostMapping("/save")
    private String saveGroup(@ModelAttribute("newGroup") Group group) {
        groupService.saveGroup(group);
        return "redirect:/group_api/allOfGroups";
    }

    @GetMapping("/update")
    private String upGroup(@RequestParam("id")Long id, Model model) {
        model.addAttribute("updateGroup", groupService.getGroupById(id));
        return "/group/updateGroup";
    }

    @PostMapping("/update")
    private String dateGroup(@ModelAttribute("updateGroup") Group group) {
        groupService.updateGroup(group);
        return "redirect:/group_api/allOfGroups";
    }

    @RequestMapping("/delete")
    private String deleteGroup(@RequestParam("id") Long id) {
        groupService.deleteGroup(id);
        return "redirect:/group_api/allOfGroups";
    }
}
