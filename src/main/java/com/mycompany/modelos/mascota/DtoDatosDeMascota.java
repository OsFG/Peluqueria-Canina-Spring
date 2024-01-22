package com.mycompany.modelos.mascota;

import com.mycompany.modelos.duenio.Duenio;
import com.mycompany.modelos.nombreCompleto.NombreCompleto;

public record DtoDatosDeMascota(
        Integer idMascota,
        String nombreMasc,
        String alergico,
        String atencionEspecial,
        Integer idDuenio,
        NombreCompleto nombreDuenio
) {
    public DtoDatosDeMascota(Mascota mascota){
        this(mascota.getId(), mascota.getNombreMasc(), mascota.getAlergico(), mascota.getAtencionEspecial(),
                mascota.getDuenio().getIdDuenio(), mascota.getDuenio().getNombreCompleto());
    }
}
