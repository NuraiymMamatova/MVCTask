package peaksoft.repository;

import peaksoft.entity.Group;

import java.util.List;

public interface GroupRepository {

    void saveGroup(Group group);

    void deleteGroup(Long id);

    void updateGroup(Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();
}
