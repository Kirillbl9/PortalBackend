package ru.nc.portal.service;

import ru.nc.portal.model.Page;

import java.util.List;

public interface PageService {
    List<Page> getAllPagesForLesson(Long lesson_id);
    Page getPageById(Long page_id);
    Page createPageForLesson(Long lesson_id, Page page);
    Page updatePageForLesson(Long lesson_id, Page page);
    void deletePageById(Long page_id);

}
