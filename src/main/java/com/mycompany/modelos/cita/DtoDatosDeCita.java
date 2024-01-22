package com.mycompany.modelos.cita;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.modelos.mascota.Mascota;
import com.mycompany.modelos.mascota.Raza;

public record DtoDatosDeCita(
        Integer idCita,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        String citaFYH,
        Integer idMascota,
        String nombreMasc,
        Raza raza,
        Integer idDuenio) {
    public DtoDatosDeCita(Cita cita){
        this(cita.getIdCita(), cita.getFechaYHora().toString(), cita.getMascota().getId(), cita.getMascota().getNombreMasc(),
                cita.getMascota().getRaza(), cita.getMascota().getDuenio().getIdDuenio());
    }

}
