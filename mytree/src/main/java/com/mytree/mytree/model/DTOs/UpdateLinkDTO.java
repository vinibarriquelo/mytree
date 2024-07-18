package com.mytree.mytree.model.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateLinkDTO {
  @NotEmpty(message = "É preciso informar o titulo!")
    private String title;

    @NotEmpty(message = "É preciso informar o link!")
    private String redirectLink;
  
}
