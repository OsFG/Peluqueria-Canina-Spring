package com.mycompany.modelos.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoDireccion(
        @NotBlank
        @Size(max = 45)
        String calle,
        @NotBlank
        String numeroExt,
        @Size(max = 30)
        String colonia,
        String codPos,
        @Size(max = 18)
        String ciudad
) {
}
