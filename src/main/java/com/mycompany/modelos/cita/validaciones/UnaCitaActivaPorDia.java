package com.mycompany.modelos.cita.validaciones;

import com.mycompany.modelos.cita.Cita;
import com.mycompany.modelos.cita.CitaRepository;
import com.mycompany.modelos.cita.DtoRegistrarCita;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UnaCitaActivaPorDia implements ValidacionAgendarCita{

    @Autowired
    private CitaRepository citaRepository;
    @Override
    public void validarDatos(DtoRegistrarCita datosCita) {
        if(datosCita.idMascota()==null && datosCita.citaFYH()==null){
            return;
        }
        var cita = citaRepository.buscarCitaActivaXFechaYMascota(datosCita.idMascota(), datosCita.citaFYH().toLocalDate()).isEmpty();
        if(!cita){
            throw new ValidationException("Ya existe una Cita activa para esta Mascota en este día");
        }
    }
}
