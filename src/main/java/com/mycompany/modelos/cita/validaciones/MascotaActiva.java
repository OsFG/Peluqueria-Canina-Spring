package com.mycompany.modelos.cita.validaciones;

import com.mycompany.modelos.cita.DtoRegistrarCita;
import com.mycompany.modelos.mascota.MascotaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class MascotaActiva implements ValidacionAgendarCita{

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public void validarDatos(DtoRegistrarCita datosCita) {
        if(datosCita.idMascota() == null){
            return; // Este bloque 'If' sirve para parar la ejecución si el 'id' es Null (ya está cubierta esta Restricción con @Null)
        }

        var mascotaActiva = mascotaRepository.findActivoById(datosCita.idMascota());
        if(!mascotaActiva){
            throw new ValidationException("Error: la Mascota está inactiva");
        }
    }
}
