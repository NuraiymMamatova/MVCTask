package peaksoft.repository;

import peaksoft.entity.Group;

import java.util.List;

public interface GroupRepository {

    void saveGroup(Long id, Group group);

    void deleteGroup(Long id);

    void updateGroup(Long id, Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();

    List<Group> getAllGroups(Long courseId);

    void assignGroupToCourse(Long groupId, Long courseId);

}
