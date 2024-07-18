package com.mytree.mytree.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mytree.mytree.jpa.LinkRepository;
import com.mytree.mytree.model.Link;
import com.mytree.mytree.model.DTOs.CreateLinkDTO;
import com.mytree.mytree.model.DTOs.LinkDTO;
import com.mytree.mytree.model.DTOs.UpdateLinkDTO;

@Service
public class LinkService {
  
  private LinkRepository _linkRepository;
  private PageService _pageService;

  public LinkService(LinkRepository linkRepository, PageService pageService) {
    this._linkRepository = linkRepository;
    this._pageService = pageService;
  }
  
  public Link createLink(CreateLinkDTO payload) {
    var page = _pageService.findPageById(payload.getPageId())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Página não localizada!"));
      
    var link = new Link(payload.getTitle(), payload.getRedirectLink(), page);
    
    return _linkRepository.save(link);
  }
  
  public Optional<Link> findLinkById(Integer linkId) {
    return _linkRepository.findById(linkId);
  }
  
  public Link updateLink (Integer linkId, UpdateLinkDTO payload) {
    var link = findLinkById(linkId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Link não localizado"));
      
    link.setTitle(payload.getTitle());
    link.setRedirectLink(payload.getRedirectLink());
    return _linkRepository.save(link);
  }
  
  public void deleteLink(Integer linkId) {
    var link = findLinkById(linkId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Link não localizado"));
    
    _linkRepository.delete(link);
  }
  
  public List<LinkDTO> findLinksByPageId(Integer pageId) {
    return _linkRepository.findLinksByPageId(pageId);
  }
}
