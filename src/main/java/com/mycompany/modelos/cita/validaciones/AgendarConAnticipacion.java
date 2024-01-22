package com.mycompany.modelos.cita.validaciones;

import com.mycompany.modelos.cita.DtoRegistrarCita;
import jakarta.validation.ValidationException;

import java.time.Duration;
import java.time.LocalDateTime;

public class AgendarConAnticipacion implements ValidacionAgendarCita{
    @Override
    public void validarDatos(DtoRegistrarCita datosCita) {

        var horaActual = LocalDateTime.now();
        var horaCita = datosCita.citaFYH();
        var diferenciaDe30Min = Duration.between(horaActual, horaCita).toMinutes()<=30;
        if (diferenciaDe30Min){
            throw new ValidationException("Error: Solicitud de Cita con menos de 30 min. de anticipación");
        }
    }
}
