package com.mycompany.modelos.duenio;

import com.mycompany.modelos.direccion.Direccion;
import com.mycompany.modelos.nombreCompleto.NombreCompleto;

public record DtoDatosDeDuenio(Integer idDuenio, NombreCompleto nombre, String telefono, String correo, Direccion direccion) {

    public DtoDatosDeDuenio(Duenio duenio){
        this(duenio.getIdDuenio(), duenio.getNombreCompleto(), duenio.getTelefono(), duenio.getCorreo(), duenio.getDireccion());
    }
}

