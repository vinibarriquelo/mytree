package com.mytree.mytree.model.DTOs;

import lombok.Data;

@Data
public class PageDTO {
    private Integer id;
    private String title;
    private String description;

    public PageDTO(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
