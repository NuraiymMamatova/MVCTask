package peaksoft.service;

import peaksoft.entity.Group;

import java.io.IOException;
import java.util.List;

public interface GroupService {

    void saveGroup(Long id,Group group);

    void deleteGroup(Long id);

    void updateGroup(Long id, Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();

    List<Group> getAllGroups(Long courseId);

    void assignGroupToCourse(Long groupId, Long courseId) throws IOException;

}
