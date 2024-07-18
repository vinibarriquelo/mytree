package com.mytree.mytree.jpa;

import com.mytree.mytree.model.DTOs.PageDTO;
import com.mytree.mytree.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    @Query("SELECT new com.mytree.mytree.model.DTOs.PageDTO(p.id, p.title, p.description)" +
            "FROM Page p " +
            "WHERE user.id = :userId")
    List<PageDTO> findPagesByUserId(@Param("userId") Integer userId);
}
