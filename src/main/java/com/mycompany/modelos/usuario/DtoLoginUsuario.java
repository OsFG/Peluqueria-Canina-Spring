package com.mycompany.modelos.usuario;

import jakarta.validation.constraints.NotBlank;

public record DtoLoginUsuario(
        @NotBlank
        String login,
        @NotBlank
        String clave) {
}
