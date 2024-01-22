package com.mycompany.modelos.mascota;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DtoActualizarDatosMascota(
        @NotNull
        Integer id,
        @Size(max = 18)
        String nombreMasc,
        Raza raza,
        @Size(max = 30)
        String color,
        @Size(max = 90)
        String alergico,
        @Size(max = 120)
        String observaciones,
        @Size(max = 90)
        String atencionEspecial) {
}
