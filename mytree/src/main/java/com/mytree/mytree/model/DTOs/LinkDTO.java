package com.mytree.mytree.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LinkDTO {

    private Integer id;
    private String title;
    private String redirectLink;
}
