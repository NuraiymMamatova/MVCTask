package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Lesson;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveLesson(Long id, Lesson lesson) {
        lessonRepository.saveLesson(id, lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteLesson(id);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        lessonRepository.updateLesson(id, lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getLessonById(id);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    @Override
    public List<Lesson> getAllLessons(Long id) {
        return lessonRepository.getAllLessons(id);
    }
}
