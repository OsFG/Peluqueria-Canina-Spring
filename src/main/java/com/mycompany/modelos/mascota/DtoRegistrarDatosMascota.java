package com.mycompany.modelos.mascota;

import com.mycompany.utilsYServiceGeneral.DtoBuscarEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DtoRegistrarDatosMascota(
        @NotBlank
        @Size(max = 18)
        String nombreMasc,
        @NotNull
        Raza raza,
        @NotBlank
        @Size(max = 30)
        String color,
        @NotBlank
        @Size(max = 90)
        String alergico,
        @NotBlank
        @Size(max = 120)
        String observaciones,
        @NotBlank
        @Size(max = 90)
        String atencionEspecial,
        @Valid
        @NotNull
        DtoBuscarEntity filtroDatoDuenio
) {
}
