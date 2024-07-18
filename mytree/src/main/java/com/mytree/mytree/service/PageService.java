package com.mytree.mytree.service;

import com.mytree.mytree.jpa.PageRepository;
import com.mytree.mytree.model.DTOs.CreatePageDTO;
import com.mytree.mytree.model.DTOs.PageDTO;
import com.mytree.mytree.model.DTOs.UpdatePageDTO;
import com.mytree.mytree.model.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PageService {

    private UserService _userService;
    private PageRepository _pageRepository;

    public PageService(UserService userService, PageRepository pageRepository) {
        this._userService = userService;
        this._pageRepository = pageRepository;
    }

    public Page createPage(CreatePageDTO payload) {
        var user = _userService.findUserById(payload.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não localizado."));

        var page = new Page(payload.getTitle(), payload.getDescription(), user);

        return  _pageRepository.save(page);
    }

    public List<PageDTO> findPagesByUserId(Integer userId) {
        _userService.findUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não localizado."));

        return  _pageRepository.findPagesByUserId(userId);
    }

    public Optional<Page> findPageById(Integer pageId) {
        return _pageRepository.findById(pageId);
    }

    public void updatePage(Integer pageId, UpdatePageDTO payload) {
        var page = findPageById(pageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pagina nõa localizada"));

        page.setTitle(payload.getTitle());
        page.setDescription(payload.getDescription());

        _pageRepository.save(page);
    }

    public void deletePage(Integer pageId) {
        var page = findPageById(pageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pagina nõa localizada"));

        _pageRepository.delete(page);
    }
}
