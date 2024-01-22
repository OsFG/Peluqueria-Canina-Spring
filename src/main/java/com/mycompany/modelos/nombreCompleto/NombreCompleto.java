package com.mycompany.modelos.nombreCompleto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class NombreCompleto{
        String nombre;
        @Column(name = "apellido_p")
        String apellidoP;
        @Column(name = "apellido_m")
        String apellidoM;
    public NombreCompleto(DtoDatosNombre datosNombre){
        this.nombre=datosNombre.nombre();
        this.apellidoP=datosNombre.apellidoP();
        this.apellidoM = datosNombre.apellidoM();
    }
}
