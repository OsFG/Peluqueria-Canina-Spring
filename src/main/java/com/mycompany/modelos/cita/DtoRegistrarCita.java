package com.mycompany.modelos.cita;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DtoRegistrarCita(
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime citaFYH,
        @NotNull
        Integer idMascota,
        @NotNull
        Integer idDuenio) {
}
