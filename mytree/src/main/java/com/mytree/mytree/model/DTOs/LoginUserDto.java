package com.mytree.mytree.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginUserDto(
        @NotEmpty(message = "É preciso preencher o email!") @Email(message = "É preciso informar um email válido!") String email,
        @NotEmpty(message = "É preciso informar a senha!") String password
) {
}
