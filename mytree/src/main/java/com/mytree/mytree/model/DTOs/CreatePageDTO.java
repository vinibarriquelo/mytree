package com.mytree.mytree.model.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreatePageDTO {
    @NotEmpty(message = "É preciso informar um título.")
    private String title;

    @NotEmpty(message = "É preciso informar uma descrição.")
    private String description;

    @NotNull(message = "É preciso informar o usuario.")
    @Positive(message = "É preciso informar um ID válido.")
    private Integer userId;
}
