package com.mytree.mytree.model.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdatePageDTO {
    @NotEmpty(message = "É preciso informar um título.")
    private String title;

    @NotEmpty(message = "É preciso informar uma descrição.")
    private String description;
}
