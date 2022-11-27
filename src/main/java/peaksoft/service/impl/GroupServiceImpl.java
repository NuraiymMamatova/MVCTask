package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Group;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void saveGroup(Long id, Group group) {
        groupRepository.saveGroup(id, group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteGroup(id);
    }

    @Override
    public void updateGroup(Long id, Group group) {
        groupRepository.updateGroup(id, group);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        return groupRepository.getAllGroups(courseId);
    }

}
