package com.mytree.mytree.model.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateLinkDTO {
  
    @NotEmpty(message = "É preciso informar o titulo!")
    private String title;

    @NotEmpty(message = "É preciso informar o link!")
    private String redirectLink;
    
    @NotNull(message = "É preciso informar a pagina.")
    @Positive(message = "É preciso informar um ID válido.")
    private Integer pageId;
}
