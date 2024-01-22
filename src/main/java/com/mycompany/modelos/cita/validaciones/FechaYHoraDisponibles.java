package com.mycompany.modelos.cita.validaciones;

import com.mycompany.modelos.cita.CitaRepository;
import com.mycompany.modelos.cita.DtoRegistrarCita;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class FechaYHoraDisponibles implements ValidacionAgendarCita{

    @Autowired
    private CitaRepository citaRepository;
    @Override
    public void validarDatos(DtoRegistrarCita datosCita) {
        var comprobacionCita = citaRepository.existsByFechaYHora(datosCita.citaFYH());
        if(comprobacionCita){
            throw new  ValidationException("Ya hay una Cita Agendada en esa fecha y hora");
        }
    }
}
