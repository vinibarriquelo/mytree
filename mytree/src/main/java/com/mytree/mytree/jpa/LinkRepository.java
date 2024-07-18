package com.mytree.mytree.jpa;
import java.util.List;

import com.mytree.mytree.model.Link;
import com.mytree.mytree.model.DTOs.LinkDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
  
  @Query("SELECT new com.mytree.mytree.model.DTOs.LinkDTO(l.id, l.title, l.redirectLink)" +
      "FROM Link l " +
      "WHERE page.id = :pageId")
  List<LinkDTO> findLinksByPageId(@Param("pageId") Integer pageId);
}
