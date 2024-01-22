package com.mycompany.modelos.duenio;

import com.mycompany.modelos.direccion.DtoDireccion;
import com.mycompany.modelos.nombreCompleto.DtoDatosNombre;
import jakarta.validation.constraints.*;

public record DtoActualizarDatosDuenio(
        @NotNull
        Integer id,
        DtoDatosNombre datosNombre,
        @Pattern(regexp = "\\d{10}")
        String telefono,
        DtoDireccion direccion
) {
}
