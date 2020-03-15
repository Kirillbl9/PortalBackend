package ru.nc.portal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nc.portal.model.Page;
import ru.nc.portal.model.dto.PageDTO;
import ru.nc.portal.service.PageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class PageController {

    @Autowired
    public PageService pageService;

    @Autowired
    public ModelMapper modelMapper;

    @GetMapping(value = "/lessons/{lesson_id}/pages")
    public List<Page> getAllPagesForLesson(@PathVariable("lesson_id") Long lesson_id){
        return pageService.getAllPagesForLesson(lesson_id);
    }

    @GetMapping(value = "/pages/{page_id}")
    public Page getPageById(@PathVariable("page_id") Long page_id){
        return pageService.getPageById(page_id);
    }

    @PostMapping(value = "/lessons/{lesson_id}/pages")
    public ResponseEntity<Page> createPageForLesson(@PathVariable("lesson_id") Long lesson_id,
                                                    @Valid @RequestBody PageDTO pageDTO){
        Page page = pageService.createPageForLesson(lesson_id, convertPageDTOToEntity(pageDTO));
        return new ResponseEntity<>(page, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/lessons/{lesson_id}/pages")
    public void updatePage(@PathVariable("lesson_id") Long lesson_id,
                           @Valid @RequestBody PageDTO pageDTO){
        pageService.updatePageForLesson(lesson_id, convertPageDTOToEntity(pageDTO));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/pages/{page_id}")
    public void deletePageById(@PathVariable("page_id") Long page_id){
        pageService.deletePageById(page_id);
    }


    private Page convertPageDTOToEntity(PageDTO pageDTO) {
        return modelMapper.map(pageDTO, Page.class);
    }
}
