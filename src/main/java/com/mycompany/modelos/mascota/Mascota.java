package com.mycompany.modelos.mascota;

import com.mycompany.modelos.cita.Cita;
import com.mycompany.modelos.duenio.Duenio;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "mascota")
@Table(name = "mascotas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of ="id")
public class Mascota {

// ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_masc")
    private String nombreMasc;
    @Enumerated(EnumType.STRING)
    private Raza raza;
    private String color;
    private String alergico;
    private String observaciones;
    @Column(name = "atencion_especial")
    private String atencionEspecial;
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_due")
    private Duenio duenio;

    @OneToMany(mappedBy = "mascota")
    private List<Cita> cita;
// CONSTRUCTOR vacío y con todos los arg (con Lombock)
    public Mascota(DtoRegistrarDatosMascota datosMascota, Duenio duenio){
        this.nombreMasc = datosMascota.nombreMasc();
        this.raza = datosMascota.raza();
        this.color = datosMascota.color();
        this.alergico = datosMascota.alergico();
        this.observaciones = datosMascota.observaciones();
        this.atencionEspecial = datosMascota.atencionEspecial();
        this.activo = true;
        this.duenio = duenio;
    }
// MÉTODOS (Getter y Setter con Lombock)
    public void actualizarDatos(DtoActualizarDatosMascota actualizarMascota){
        if(!actualizarMascota.nombreMasc().isBlank()){
            this.nombreMasc = actualizarMascota.nombreMasc();
        }
        if(actualizarMascota.raza() != null){
            this.raza = actualizarMascota.raza();
        }
        if(actualizarMascota.color() != null && !actualizarMascota.color().trim().isBlank()){
            this.color = actualizarMascota.color();
        }
        if(actualizarMascota.alergico()!= null && !actualizarMascota.alergico().trim().isBlank()){
            this.alergico = actualizarMascota.alergico();
        }
        if(actualizarMascota.observaciones()!= null && !actualizarMascota.observaciones().trim().isBlank()){
            this.observaciones = actualizarMascota.observaciones();
        }
        if(actualizarMascota.atencionEspecial()!= null && !actualizarMascota.atencionEspecial().trim().isBlank()){
            this.atencionEspecial = actualizarMascota.atencionEspecial();
        }
    }

    public void desactivarMascota(){
        this.activo = false;
    }
}
