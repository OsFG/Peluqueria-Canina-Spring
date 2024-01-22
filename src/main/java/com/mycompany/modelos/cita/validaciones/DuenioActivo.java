package com.mycompany.modelos.cita.validaciones;

import com.mycompany.modelos.cita.DtoRegistrarCita;
import com.mycompany.modelos.duenio.DuenioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class DuenioActivo implements ValidacionAgendarCita{

    @Autowired
    private DuenioRepository duenioRepository;
    @Override
    public void validarDatos(DtoRegistrarCita datosCita) {
        var duenioAct = duenioRepository.findActivoById(datosCita.idDuenio());
        if(!duenioAct){
            throw new ValidationException("Error: el Dueño está inactivo");
        }
    }
}
