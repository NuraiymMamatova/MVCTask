package peaksoft.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public void saveGroup(Group group) {
        groupRepository.saveGroup(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteGroup(id);
    }

    @Override
    public void updateGroup(Group group) {
        groupRepository.updateGroup(group);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }
}
