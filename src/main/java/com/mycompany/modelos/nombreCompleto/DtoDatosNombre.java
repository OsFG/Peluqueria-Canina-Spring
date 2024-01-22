package com.mycompany.modelos.nombreCompleto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoDatosNombre(
        @NotBlank
        @Size(min = 3, max = 27)
        String nombre,
        @NotBlank
        @Size(min = 3, max = 12)
        String apellidoP,
        @NotBlank
        @Size(min = 3, max = 12)
        String apellidoM
) {
}
