package com.mytree.mytree.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUserDTO {
    @NotEmpty(message = "É preciso informar o nome.")
    private String name;

    @NotEmpty(message = "É preciso informar o email.")
    @Email(message = "É preciso informar um email válido.")
    private String email;

    @NotEmpty(message = "É preciso informar a senha.")
    @Length(min = 6, message = "É preciso ter pelo menos 6 caractares.")
    private String password;
}
