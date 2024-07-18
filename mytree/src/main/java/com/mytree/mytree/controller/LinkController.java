package com.mytree.mytree.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mytree.mytree.model.Link;
import com.mytree.mytree.model.DTOs.CreateLinkDTO;
import com.mytree.mytree.model.DTOs.LinkDTO;
import com.mytree.mytree.model.DTOs.UpdateLinkDTO;
import com.mytree.mytree.service.LinkService;

@RestController
@RequestMapping("link")
public class LinkController {
  
  private LinkService _LinkService;
 
  public LinkController(LinkService _LinkService) {
    this._LinkService = _LinkService;
  }

  @PostMapping()
  public ResponseEntity<Link> createLink(@Valid @RequestBody CreateLinkDTO payload) {
    var link = _LinkService.createLink(payload);
    link.getPage().getUser().setPassword("******");
    return ResponseEntity.status(HttpStatus.CREATED).body(link);
  }
  
  @PutMapping("{linkId}")
  public ResponseEntity<Link> updateLink(@PathVariable("linkId") Integer linkId, @Valid @RequestBody UpdateLinkDTO payload) {
    var link = _LinkService.updateLink(linkId, payload);
    link.getPage().getUser().setPassword("******");
    return ResponseEntity.status(HttpStatus.OK).body(link);
  }
  
  @DeleteMapping("{linkId}")
  public ResponseEntity deleteLink(@PathVariable("linkId") Integer linkId) {
    _LinkService.deleteLink(linkId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
  
  @GetMapping("{pageId}")
  public ResponseEntity<List<LinkDTO>> findLinksByPageId(@PathVariable("pageId") Integer pageId) {
    var links = _LinkService.findLinksByPageId(pageId);
    return ResponseEntity.status(HttpStatus.OK).body(links);
  }
  
}
