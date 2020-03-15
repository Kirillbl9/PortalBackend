package ru.nc.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nc.portal.model.Lesson;
import ru.nc.portal.model.Page;
import ru.nc.portal.repository.LessonRepository;
import ru.nc.portal.repository.PageRepository;
import ru.nc.portal.service.PageService;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Page> getAllPagesForLesson(Long lesson_id) {
        return pageRepository.getAllPagesByLessonId(lesson_id);
    }

    @Override
    public Page getPageById(Long page_id) {
        return pageRepository.findById(page_id).orElse(null);
    }

    @Override
    public Page createPageForLesson(Long lesson_id, Page page) {
        Lesson lesson = lessonRepository.findById(lesson_id).orElse(null);
        //incr num of all pages that has num more or eq this
        List<Page> pages = pageRepository.getAllByNumberIsGreaterThanEqual(page.getNumber());
        for(Page p: pages){
            p.setNumber(p.getNumber() + 1);
        }
        pageRepository.saveAll(pages);
        page.setLesson(lesson);
        return pageRepository.save(page);
    }

    @Override
    public Page updatePageForLesson(Long lesson_id, Page page) {
        Lesson lesson = lessonRepository.findById(lesson_id).orElse(null);
        page.setLesson(lesson);
        return pageRepository.save(page);
    }

    @Override
    public void deletePageById(Long page_id) {
        Page page = pageRepository.findById(page_id).orElse(null);
        if (page != null){
            List<Page> pages = pageRepository.getAllByNumberIsGreaterThanEqual(page.getNumber());
            for(Page p: pages){
                p.setNumber(p.getNumber()-1);
            }
            pageRepository.deleteById(page_id);
        }
    }
}
