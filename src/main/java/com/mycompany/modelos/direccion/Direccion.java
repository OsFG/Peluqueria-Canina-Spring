package com.mycompany.modelos.direccion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Direccion{

    String calle;
    @Column(name = "numero_ext")
    String numeroExt;
    String colonia;
    @Column(name = "cod_pos")
    String codPos;
    String ciudad;

    public Direccion(DtoDireccion datosDireccion){
        this.calle = datosDireccion.calle();
        this.numeroExt= datosDireccion.numeroExt();
        this.colonia = datosDireccion.colonia();
        this.codPos = datosDireccion.codPos();
        this.ciudad = datosDireccion.ciudad();
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodPos() {
        return codPos;
    }

    public void setCodPos(String codPos) {
        this.codPos = codPos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
