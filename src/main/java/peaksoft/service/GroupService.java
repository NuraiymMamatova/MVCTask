package peaksoft.service;

import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {

    void saveGroup(Group group);

    void deleteGroup(Long id);

    void updateGroup(Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();
}
