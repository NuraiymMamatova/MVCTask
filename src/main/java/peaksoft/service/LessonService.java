package peaksoft.service;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {

    void saveLesson(Lesson lesson);

    void deleteLesson(Long id);

    void updateLesson(Lesson lesson);

    Lesson getLessonById(Long id);

    List<Lesson> getAllLessons();
}
