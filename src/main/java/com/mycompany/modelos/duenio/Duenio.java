package com.mycompany.modelos.duenio;

import com.mycompany.modelos.direccion.Direccion;
import com.mycompany.modelos.mascota.DtoRegistrarDatosMascota;
import com.mycompany.modelos.mascota.Mascota;
import com.mycompany.modelos.nombreCompleto.NombreCompleto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "duenio")
@Table(name = "duenios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of ="idDuenio")
public class Duenio{

// ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idDuenio;
    @Embedded
    private NombreCompleto nombreCompleto;
    private String telefono;
    private String correo;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    @OneToMany(mappedBy = "duenio")
    private List<Mascota> idM;

    public Duenio(DtoRegistrarDatosDuenio datosRegistroDuenio) {
        this.nombreCompleto= new NombreCompleto(datosRegistroDuenio.nombre());
        this.telefono=datosRegistroDuenio.telefono();
        this.correo=datosRegistroDuenio.correo();
        this.direccion= new Direccion(datosRegistroDuenio.direccion());
        this.activo=true;
    }

// CONSTRUCTORES (con Lombok)

// MÉTODOS (Getters y Setter con Lombok)


    public void actualizarDatos(DtoActualizarDatosDuenio actualizarDatosDuenio) {
        if(actualizarDatosDuenio.datosNombre() != null){
            this.nombreCompleto=new NombreCompleto(actualizarDatosDuenio.datosNombre());
        }
        if(actualizarDatosDuenio.telefono() != null){
            this.telefono=actualizarDatosDuenio.telefono();
        }
       if(actualizarDatosDuenio.direccion() != null){
            this.direccion= new Direccion(actualizarDatosDuenio.direccion());
        }
    }

    public void desactivar() {
        this.activo = false;
    }

}
