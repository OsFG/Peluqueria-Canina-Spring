package com.mycompany.modelos.duenio;

import com.mycompany.modelos.direccion.DtoDireccion;
import com.mycompany.modelos.nombreCompleto.DtoDatosNombre;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DtoRegistrarDatosDuenio(
        @Valid
        @NotNull
        DtoDatosNombre nombre,
        @NotBlank
        @Pattern(regexp = "\\d{10}")
        String telefono,
        @Email
        @NotBlank
        String correo,
        @Valid
        @NotNull
        DtoDireccion direccion
) {
}
