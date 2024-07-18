package com.mytree.mytree.controller;

import com.mytree.mytree.model.DTOs.CreatePageDTO;
import com.mytree.mytree.model.DTOs.PageDTO;
import com.mytree.mytree.model.DTOs.UpdatePageDTO;
import com.mytree.mytree.model.Page;
import com.mytree.mytree.service.PageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("page")
public class PageController {

    private PageService _pageService;

    public PageController(PageService _pageService) {
        this._pageService = _pageService;
    }

    @PostMapping()
    public ResponseEntity<Page> createPage(@Valid @RequestBody CreatePageDTO payload) {
        var page = _pageService.createPage(payload);
        page.getUser().setPassword("******");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(page);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<PageDTO>> findPagesByUserId(@PathVariable Integer userId) {
        var pages = _pageService.findPagesByUserId(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pages);
    }

    @PutMapping("{pageId}")
    public ResponseEntity updatePage(@PathVariable("pageId") Integer pageId,@Valid @RequestBody UpdatePageDTO payload) {
        _pageService.updatePage(pageId, payload);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{pageId}")
    public ResponseEntity deletePage(@PathVariable("pageId") Integer pageId) {
        _pageService.deletePage(pageId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
